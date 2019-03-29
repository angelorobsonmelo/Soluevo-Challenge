package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.AttachmentsFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.ContractForm
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.state_progress_bar_footer_button_layout.*

class AttachmentsFormActivity : StateProgressBarBaseActivity() {

    companion object {
        const val ATTACHMENTS_IDENTIFIER = "ATTACHMENTS_IDENTIFIER"
    }

    private lateinit var binding: AttachmentsFormActivityBinding
    private lateinit var validator: Validator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attachments_form_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.attachments_form_activity)
        changeTextButtonNextToConclude()
        setupElements()
        injectCommonViews()
        injectBackView()
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
                val personal = getPersonalFromBundle()
                val vehicle = getVehicleFromBundle()
                val creditor = getCreditorFromBundle()
                val contract = getContractFromBundle()
                print(contract.amountMonths)
            }

            R.id.btnBack -> finish()
        }
    }

    private fun getPersonalFromBundle() : ContractForm.PersonalForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as ContractForm.PersonalForm
    }

    private fun getVehicleFromBundle() : ContractForm.VehicleForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(VehicleActivity.VEHICLE_IDENTIFIER) as ContractForm.VehicleForm
    }

    private fun getCreditorFromBundle() : ContractForm.CreditorForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(CreditorFormActivity.CREDITOR_IDENTIFIER) as ContractForm.CreditorForm
    }

    private fun getContractFromBundle() : ContractForm.ContractsForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(ContractFormActivity.CONTRACT_IDENTIFIER) as ContractForm.ContractsForm
    }

}
