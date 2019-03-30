package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.savecontract.SaveContractViewModel
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.adapter.AttachmentsAdapter
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.application.utils.RandomUtil
import com.angelomelo.soluevochallenge.application.utils.extensions.extractNumbers
import com.angelomelo.soluevochallenge.databinding.AttachmentsFormActivityBinding
import com.angelomelo.soluevochallenge.domain.*
import com.angelomelo.soluevochallenge.domain.form.ContractsForm
import com.angelomelo.soluevochallenge.domain.form.CreditorForm
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.angelomelo.soluevochallenge.domain.form.VehicleForm
import com.angelomelo.soluevochallenge.domain.request.*
import com.google.gson.Gson
import com.kofigyan.stateprogressbar.StateProgressBar
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.state_progress_bar_footer_button_layout.*
import net.alhazmy13.mediapicker.Image.ImagePicker
import android.graphics.Bitmap
import android.util.Base64
import com.angelomelo.soluevochallenge.R
import java.io.ByteArrayOutputStream


class AttachmentsFormActivity : StateProgressBarBaseActivity(), AttachmentsHandler {

    companion object {
        const val ATTACHMENTS_IDENTIFIER = "ATTACHMENTS_IDENTIFIER"
    }

    private lateinit var binding: AttachmentsFormActivityBinding
    private lateinit var validator: Validator
    private lateinit var contractViewModel: SaveContractViewModel
    private lateinit var attachmentViewModel: AttachmentsViewModel
    private lateinit var adapter: AttachmentsAdapter
    private var attachments = mutableListOf<Attachment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attachments_form_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.attachments_form_activity)
        contractViewModel = ViewModelProviders.of(this).get(SaveContractViewModel::class.java)
        attachmentViewModel = ViewModelProviders.of(this).get(AttachmentsViewModel::class.java)

        changeTextButtonNextToConclude()
        setupElements()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE)
    }

    private fun setupElements() {
        injectCommonViews()
        injectBackView()
        initSaveContractObserveOnSuccess()
        initSaveContractObserveOnError()
        initAttachmentObserveOnSuccess()
        initAttachmentObserveOnError()
        setupBinding()
        setupValidator()
        initAdapter()
        setupRecyclerView()
    }

    private fun changeTextButtonNextToConclude() {
        btnNext.text = getString(R.string.conclude)
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.handler = this
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    private fun initAdapter() {
        adapter = AttachmentsAdapter(attachments)
    }

    private fun setupRecyclerView() {
        val linearLayoutHorizontal = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = binding.attachmentsRecyclerView

        recyclerView.layoutManager = linearLayoutHorizontal
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
//                contractViewModel.saveContract(getRequestObjectsForm())
                  attachmentViewModel.save(attachments.first())
            }

            R.id.btnBack -> finish()
        }
    }

    private fun getRequestObjectsForm(): RequestObjectsForm {
        val gson = Gson()
        val uuid= SoluevoChallengeApplication.mSessionUseCase!!.getAuthSession()?.user?.uuid!!

        val vehicleRequest = RequestFormBase(gson.toJson(getDataVehicle()), uuid, RandomUtil.getRandomNumber().toBigInteger())
        val contractRequest = RequestFormBase(gson.toJson(getDataContract()), uuid, getContractsRequest().code.toBigInteger())
        val creditorRequest = RequestFormBase(gson.toJson(getDataCreditor()), uuid, RandomUtil.getRandomNumber().toBigInteger())

        return RequestObjectsForm(
            vehicleRequest,
            contractRequest,
            creditorRequest
        )
    }

    private fun getDataVehicle(): DataVehicle {
        val vehicleRequest = getVehicleRequest()

        return DataVehicle(
            vehicleRequest
        )
    }

    private fun getDataContract(): DataContract {
        val personalRequest = getPersonalRequest()
        val contract = getContractsRequest()

        return DataContract(
            personalRequest,
            contract
        )
    }

    private fun getDataCreditor(): DataCreditor {
        val personalRequest = getPersonalRequest()
        val creditor = getCreditorRequest()

        return DataCreditor(
            personalRequest,
            creditor
        )
    }

    private fun getPersonalRequest(): Personal {
        val personalForm = getPersonalFromBundle()

        return Personal(
            personalForm.name,
            personalForm.rg
        )
    }

    private fun getVehicleRequest(): Vehicle {
        val vehicleForm = getVehicleFromBundle()

        return Vehicle(
            vehicleForm.redial,
            vehicleForm.renavam.toBigInteger(),
            vehicleForm.ufPlate,
            vehicleForm.chassis
        )
    }

    private fun getCreditorRequest(): Creditor {
        val creditorForm = getCreditorFromBundle()

        return Creditor(
            creditorForm.address,
            creditorForm.cep.extractNumbers(),
            creditorForm.uf,
            creditorForm.addressNumber.toInt(),
            creditorForm.county,
            creditorForm.addressComplementNumber,
            creditorForm.nameFinancialAgentFinancialInstitution,
            creditorForm.cnpj.extractNumbers(),
            creditorForm.telephone,
            creditorForm.neighborhood
        )
    }

    private fun getContractsRequest(): Contract {
        val contractForm = getContractFromBundle()

        return Contract(
            contractForm.contractDate.extractNumbers(),
            contractForm.code,
            contractForm.tagNumber.toInt(),
            contractForm.amountMonths.toInt(),
            contractForm.typeRestriction,
            contractForm.rateMora.toBigDecimal(),
            contractForm.valueMoraRate.toBigDecimal(),
            contractForm.feeFineRate.toBigDecimal(),
            contractForm.valueFeeFineRate.toBigDecimal(),
            contractForm.valueContractRate.toBigDecimal(),
            contractForm.valueInterestMonth.toBigDecimal(),
            contractForm.iofValue.toBigDecimal(),
            contractForm.valueYearInterest.toBigDecimal(),
            contractForm.indexes,
            contractForm.commissionStatement,
            contractForm.commission.toBigDecimal(),
            contractForm.penaltyIndication
        )
    }


    private fun getPersonalFromBundle(): PersonalForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as PersonalForm
    }

    private fun getVehicleFromBundle(): VehicleForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(VehicleActivity.VEHICLE_IDENTIFIER) as VehicleForm
    }

    private fun getCreditorFromBundle(): CreditorForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(CreditorFormActivity.CREDITOR_IDENTIFIER) as CreditorForm
    }

    private fun getContractFromBundle(): ContractsForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(ContractFormActivity.CONTRACT_IDENTIFIER) as ContractsForm
    }

    private fun initSaveContractObserveOnSuccess() {
        contractViewModel.successObserver.observe(this, Observer {
            print("message")
        })
    }

    private fun initSaveContractObserveOnError() {
        contractViewModel.errorObserver.observe(this, Observer {
            print("message")
        })
    }

    private fun initAttachmentObserveOnSuccess() {
        contractViewModel.successObserver.observe(this, Observer {
            print("message")
        })
    }

    private fun initAttachmentObserveOnError() {
        contractViewModel.errorObserver.observe(this, Observer {
            print("message")
        })
    }

    override fun onPressOpenImagePicker() {
        ImagePicker.Builder(this@AttachmentsFormActivity)
            .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
            .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
            .directory(ImagePicker.Directory.DEFAULT)
            .extension(ImagePicker.Extension.PNG)
            .scale(600, 600)
            .allowMultipleImages(true)
            .enableDebuggingMode(true)
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val mPaths = data!!.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH)

            mPaths.forEach {
                val filename = it.substring(it.lastIndexOf("/") + 1)
                val fileExtension = it.substring(it.lastIndexOf(".") + 1)
                val fileContent = BitmapFactory.decodeFile(it)

               val attachment = Attachment(
//                    getContractsRequest().code.toBigInteger(),
                    1.toBigInteger(),
                    fileExtension,
                    filename,
                    encodeTobase64(fileContent),
                    it
                )

                attachments.add(attachment)
            }

            setupAdapter()
        }

    }

    fun encodeTobase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 90, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun setupAdapter() {
        adapter = AttachmentsAdapter(attachments)
        binding.attachmentsRecyclerView.adapter = ScaleInAnimationAdapter(adapter).apply {
            setFirstOnly(false)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }

        adapter.notifyDataSetChanged()
    }

}
