package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.savecontract.SaveContractViewModel
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.application.utils.extensions.extractNumbers
import com.angelomelo.soluevochallenge.databinding.AttachmentsFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.ContractsForm
import com.angelomelo.soluevochallenge.domain.form.CreditorForm
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.angelomelo.soluevochallenge.domain.form.VehicleForm
import com.angelomelo.soluevochallenge.domain.request.*
import com.google.gson.Gson
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.state_progress_bar_footer_button_layout.*

class AttachmentsFormActivity : StateProgressBarBaseActivity() {

    companion object {
        const val ATTACHMENTS_IDENTIFIER = "ATTACHMENTS_IDENTIFIER"
    }


    private lateinit var binding: AttachmentsFormActivityBinding
    private lateinit var validator: Validator
    private lateinit var viewModel: SaveContractViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attachments_form_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.attachments_form_activity)
        viewModel = ViewModelProviders.of(this).get(SaveContractViewModel::class.java)

        changeTextButtonNextToConclude()
        setupElements()
        injectCommonViews()
        injectBackView()
        initObserveOnSuccess()
        initObserveOnError()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE)
    }

    private fun setupElements() {
        setupBinding()
        setupValidator()
    }

    private fun changeTextButtonNextToConclude() {
        btnNext.text = getString(R.string.conclude)
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                viewModel.saveContract(getContractRequest())
            }

            R.id.btnBack -> finish()
        }
    }

    private fun getContractRequest(): ContractRequest {
        val data = getData()
        val dataToJson = Gson().toJson(data)

        val uuid= SoluevoChallengeApplication.mSessionUseCase!!.getAuthSession()?.user?.uuid!!
        return ContractRequest(dataToJson, uuid, getContractsRequest().code.toBigInteger())
    }

    private fun getData(): Data {
        val personalRequest = getPersonalRequest()
        val vehicleRequest = getVehicleRequest()
        val creditorRequest = getCreditorRequest()
        val contractsRequest = getContractsRequest()

        return Data(
            personalRequest,
            vehicleRequest,
            creditorRequest,
            contractsRequest
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
            vehicleForm.renavam,
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

    private fun getContractsRequest(): Contracts {
        val contractForm = getContractFromBundle()

        return Contracts(
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

    private fun initObserveOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            print(it.code)
        })
    }

    private fun initObserveOnError() {
        viewModel.errorObserver.observe(this, Observer {

        })
    }

}
