package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.main.MainActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.SaveContractViewModel
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.adapter.AttachmentsAdapter
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.application.utils.RandomUtil
import com.angelomelo.soluevochallenge.application.utils.extensions.encodeTobase64
import com.angelomelo.soluevochallenge.application.utils.extensions.extractNumbers
import com.angelomelo.soluevochallenge.application.utils.extensions.getFileExntesion
import com.angelomelo.soluevochallenge.application.utils.extensions.getFileName
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
import kotlinx.android.synthetic.main.contract_code_dialog.*
import kotlinx.android.synthetic.main.state_progress_bar_footer_button_layout.*
import net.alhazmy13.mediapicker.Image.ImagePicker
import java.math.BigInteger
import java.util.*


class AttachmentsFormActivity : StateProgressBarBaseActivity(), AttachmentsHandler {

    private lateinit var binding: AttachmentsFormActivityBinding
    private lateinit var validator: Validator
    private lateinit var contractViewModel: SaveContractViewModel
    private lateinit var attachmentViewModel: AttachmentsViewModel
    private lateinit var adapter: AttachmentsAdapter
    private var attachments = mutableListOf<Attachment>()
    private var isButtonSaveClicked = false
    private var contractCode: BigInteger? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attachments_form_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.attachments_form_activity)
        contractViewModel = ViewModelProviders.of(this).get(SaveContractViewModel::class.java)
        attachmentViewModel = ViewModelProviders.of(this).get(AttachmentsViewModel::class.java)

        setupElements()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE)
    }

    private fun setupElements() {
        setupToolbar(getString(R.string.attach_pictures))
        changeTextButtonNextToConclude()
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
        binding.viewModel = attachmentViewModel
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    private fun initAdapter() {
        adapter = AttachmentsAdapter(attachments, this)
    }

    private fun setupRecyclerView() {
        val linearLayoutHorizontal = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = binding.attachmentsRecyclerView

        recyclerView.layoutManager = linearLayoutHorizontal
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                when {
                    isButtonSaveClicked && attachments.isNotEmpty() -> {
                        if (haveAllAttachmentsBeenSubmitted()) {
                            showAlertSentImages()
                            return
                        }

                        saveAttachments()
                        return
                    }
                    else -> {
                        isButtonSaveClicked = true
                        contractViewModel.saveContract(getRequestObjectsForm())
                    }
                }

            }

            R.id.btnBack -> finish()
        }
    }

    private fun getRequestObjectsForm(): RequestObjectsForm {
        val gson = Gson()
        val uuid = SoluevoChallengeApplication.mSessionUseCase!!.getAuthSession()?.user?.uuid!!

        val vehicleRequest =
            RequestFormBase(gson.toJson(getDataVehicle()), uuid, RandomUtil.getRandomNumber().toBigInteger())
        val contractRequest =
            RequestFormBase(gson.toJson(getDataContract()), uuid, getContractsRequest().code.toBigInteger())
        val creditorRequest =
            RequestFormBase(gson.toJson(getDataCreditor()), uuid, RandomUtil.getRandomNumber().toBigInteger())

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
            creditorForm.addressNumber.toBigInteger(),
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
            contractForm.tagNumber.toBigInteger(),
            contractForm.amountMonths.toBigInteger(),
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
            showAlertSuccess(it)
            disableBackButton()
            if (haveAttachmentsSelected()) {
                disableRemoveImageClick()
                notifyAdapterThatSaveButtonWasClicked()
                saveAttachments()
                isButtonSaveClicked = true
            }
        })
    }

    private fun saveAttachments() {
        attachments.forEach {
            attachmentViewModel.save(it)
        }
    }

    private fun haveAttachmentsSelected() = attachments.isNotEmpty()

    private fun notifyAdapterThatSaveButtonWasClicked() {
        adapter.saveButtonWasClicked = true
        adapter.notifyDataSetChanged()
    }

    private fun disableRemoveImageClick() {
        binding.imagePickerButton.isEnabled = false
    }

    private fun initSaveContractObserveOnError() {
        contractViewModel.errorObserver.observe(this, Observer {
            isButtonSaveClicked = false
            showAlertReEnterContractCode()
        })
    }

    private fun disableBackButton() {
        backBtn.isEnabled = false
    }

    private fun showAlertReEnterContractCode() {
        AlertDialog.Builder(themedContext)
            .setTitle(getString(R.string.re_enter_contract_number))
            .setMessage(getString(R.string.re_enter_contract_number_message))
            .setView(layoutInflater.inflate(R.layout.contract_code_dialog, null))
            .setPositiveButton(getString(R.string.media_picker_ok)) { dialog, _ ->
                val dialogInstance = dialog as Dialog
                val contractCodeEditText = dialogInstance.contract_code_alert_edit_text
                val newObjecRequestWithAnotherContractCode = getRequestObjectsForm()

                if (contractCodeEditText.text?.isNotEmpty()!!) {
                    contractCode = contractCodeEditText.rawText?.toBigInteger()
                    newObjecRequestWithAnotherContractCode.contractRequest.code = contractCode!!
                    contractViewModel.saveContract(newObjecRequestWithAnotherContractCode)

                    isButtonSaveClicked = true
                    dialog.cancel()
                    dialog.dismiss()
                    return@setPositiveButton
                }

                showAlertContractCodeNotEmpty()
            }
            .setNegativeButton(getString(R.string.media_picker_cancel)) { dialog, _ ->
                dialog.cancel()
                dialog.dismiss()
                isButtonSaveClicked = true
            }
            .show()
    }

    private fun initAttachmentObserveOnSuccess() {
        attachmentViewModel.successObserver.observe(this, Observer {
            disableBackButton()
            setSuccessfullyUploadedImageIndicator(it)
            checkIfItLastAttachment(it)
        })
    }

    private fun checkIfItLastAttachment(it: Attachment) {
        if (isLastAttachment(it)) {
            enableImagePickerButton()
            showAlertSuccessAttachments(it.contractCode)
        }
    }

    private fun enableImagePickerButton() {
        binding.imagePickerButton.isEnabled = true
    }

    private fun isLastAttachment(it: Attachment) = attachments.last().path == it.path

    private fun setSuccessfullyUploadedImageIndicator(it: Attachment) {
        val index = attachments.indexOf(it)
        attachments[index].wasSent = true
        adapter.notifyItemChanged(index)
    }

    private fun initAttachmentObserveOnError() {
        attachmentViewModel.errorObserver.observe(this, Observer {
            showAlertErrorSubmittingImages()
            enableImagePickerButton()
            setThatSavedButtonWasNotClickedSoThatTheUserCanClickItAgain()
        })
    }

    private fun setThatSavedButtonWasNotClickedSoThatTheUserCanClickItAgain() {
        adapter.saveButtonWasClicked = false
        adapter.notifyDataSetChanged()
    }

    override fun onPressOpenImagePicker() {
        checkIfSaveButtonHasAlreadyBeenClickedWhenUserClicksToChooseAttachments()
        showImagePickerOptions()
    }

    private fun showImagePickerOptions() {
        if (isButtonSaveClicked) {
            removeSentAttachments()
        }

        ImagePicker.Builder(this@AttachmentsFormActivity)
            .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
            .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
            .directory(ImagePicker.Directory.DEFAULT)
            .extension(ImagePicker.Extension.PNG)
            .scale(600, 600)
            .allowMultipleImages(true)
            .enableDebuggingMode(true)
            .allowOnlineImages(true)
            .build()
    }

    private fun removeSentAttachments() {
        if (attachments.isNotEmpty()) {
            val attachtmentSent = attachments.filter { it.wasSent }.map { it }
            attachments.removeAll(attachtmentSent)
        }
    }

    private fun haveAllAttachmentsBeenSubmitted(): Boolean {
        val attachamentsSent = attachments.filter { it.wasSent }.map { it }
        return attachamentsSent.size == attachments.size
    }

    private fun checkIfSaveButtonHasAlreadyBeenClickedWhenUserClicksToChooseAttachments() {
        if (adapter.saveButtonWasClicked) {
            removeAllAttachmentsAndUpdateAdapter()
        }
    }

    private fun removeAllAttachmentsAndUpdateAdapter() {
        attachments.clear()
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val mPaths = getAttachmentPaths(data)
            extractingFilesByPathAndPopulateObjectToShowOnAdapter(mPaths ?: arrayListOf(""))
            setupAdapter()
        }
    }

    private fun getAttachmentPaths(data: Intent?) =
        data?.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH)

    private fun extractingFilesByPathAndPopulateObjectToShowOnAdapter(mPaths: ArrayList<String>) {
        val contractCode: BigInteger? = getContractCode()
        val pathsNotNull = mPaths.mapNotNull { it }

        pathsNotNull.forEach {
            val attachment = Attachment(
                contractCode!!,
                it.getFileExntesion() ?: "",
                it.getFileName() ?: "",
                getBitmap(it).encodeTobase64() ?: "",
                it
            )

            attachments.add(attachment)
        }
    }

    private fun getContractCode(): BigInteger? {
        return if (isButtonSaveClicked && contractCode != null) {
            this.contractCode
        } else {
            getContractsRequest().code.toBigInteger()
        }
    }

    private fun getBitmap(it: String) = BitmapFactory.decodeFile(it)

    private fun setupAdapter() {
        adapter = AttachmentsAdapter(attachments, this)
        binding.attachmentsRecyclerView.adapter = ScaleInAnimationAdapter(adapter).apply {
            setFirstOnly(false)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }

    }

    override fun onPressRemoveImage(attachment: Attachment) {
        val index = attachments.indexOf(attachment)
        attachments.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onPressBackToContractListScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}
