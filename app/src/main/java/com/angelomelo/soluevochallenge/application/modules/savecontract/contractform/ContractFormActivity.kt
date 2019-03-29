package com.angelomelo.soluevochallenge.application.modules.savecontract.contractform


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.AttachmentsActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.credorform.CredorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.ActivityContractFormBinding
import com.angelomelo.soluevochallenge.domain.form.ContractForm
import com.angelomelo.soluevochallenge.domain.form.ContractForm.ContractsForm
import com.kofigyan.stateprogressbar.StateProgressBar

class ContractFormActivity : UsageBaseActivity() {

    companion object {
        const val CONTRACT_IDENTIFIER = "CONTRACT_IDENTIFIER"
    }

    private lateinit var binding: ActivityContractFormBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contract_form)
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
        val intent = Intent(applicationContext, AttachmentsActivity::class.java)
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
        intent.putExtra(CredorFormActivity.CREDITOR_IDENTIFIER, creditor)
    }

    private fun putContractInExtra(intent: Intent) {
        val contract = binding.contract
        intent.putExtra(CONTRACT_IDENTIFIER, contract)
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
        return bundle?.getParcelable(CredorFormActivity.CREDITOR_IDENTIFIER) as ContractForm.CreditorForm
    }

}
