package com.angelomelo.soluevochallenge.application.modules.savecontract.contractform


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.AttachmentsFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.ContractFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.ContractsForm
import com.angelomelo.soluevochallenge.domain.form.CreditorForm
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.angelomelo.soluevochallenge.domain.form.VehicleForm
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.contract_form_activity.*

class ContractFormActivity : StateProgressBarBaseActivity() {

    companion object {
        const val CONTRACT_IDENTIFIER = "CONTRACT_IDENTIFIER"
    }

    private lateinit var binding: ContractFormActivityBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.contract_form_activity)
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.contract       = ContractsForm()
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                if (validator.validate()) {
                    putObjectsFromTheStepsOfTheFormAndGoToContractForm()
                }
            }

            R.id.btnBack -> finish()
        }
    }

    private fun putObjectsFromTheStepsOfTheFormAndGoToContractForm() {
        val intent = Intent(applicationContext, AttachmentsFormActivity::class.java)
        putPersonalInExtra(intent)
        putVehicleInExtra(intent)
        putCreditorInExtra(intent)
        putContractInExtra(intent)

        startActivity(intent)
    }

    private fun putPersonalInExtra(intent: Intent) {
        val personal = getPersonalFromBundle()
        intent.putExtra(PersonalFormActivity.PERSONAL_IDENTIFIER, personal)
    }

    private fun putVehicleInExtra(intent: Intent) {
        val vehicle = getVehicleFromBundle()
        intent.putExtra(VehicleActivity.VEHICLE_IDENTIFIER, vehicle)
    }

    private fun putCreditorInExtra(intent: Intent) {
        val creditor = getCreditorFromBundle()
        intent.putExtra(CreditorFormActivity.CREDITOR_IDENTIFIER, creditor)
    }

    private fun putContractInExtra(intent: Intent) {
        val contract   = binding.contract
        contract?.rateMora           = binding.moraRateEditText.value.toString()
        contract?.valueMoraRate      = binding.valueMoraRateEditText.value.toString()
        contract?.feeFineRate        = binding.fineRateEditText.value.toString()
        contract?.valueFeeFineRate   = binding.valueFineRateEditText.value.toString()
        contract?.valueContractRate  = binding.valueContractRateEditText.value.toString()
        contract?.valueInterestMonth = binding.interestAtTheMonthEditText.value.toString()
        contract?.iofValue           = binding.valueIofEditText.value.toString()
        contract?.valueYearInterest  = binding.interestRatePerYearEditText.value.toString()
        contract?.commission         = binding.commisionEditText.value.toString()
        contract?.penaltyIndication   = binding.penaltyIndicationEditText.value.toString()
        contract?.commissionStatement = binding.indicationCommissionEditText.value.toString()

        intent.putExtra(CONTRACT_IDENTIFIER, contract)
    }

    private fun getPersonalFromBundle() : PersonalForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as PersonalForm
    }

    private fun getVehicleFromBundle() : VehicleForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(VehicleActivity.VEHICLE_IDENTIFIER) as VehicleForm
    }

    private fun getCreditorFromBundle() : CreditorForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(CreditorFormActivity.CREDITOR_IDENTIFIER) as CreditorForm
    }

}
