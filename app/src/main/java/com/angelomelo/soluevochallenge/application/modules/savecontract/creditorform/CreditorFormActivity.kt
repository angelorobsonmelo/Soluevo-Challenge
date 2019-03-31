package com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.CreditorFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.CreditorForm
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.angelomelo.soluevochallenge.domain.form.VehicleForm
import com.kofigyan.stateprogressbar.StateProgressBar

class CreditorFormActivity : StateProgressBarBaseActivity() {

    companion object {
        const val CREDITOR_IDENTIFIER = "CREDITOR_IDENTIFIER"
    }

    private lateinit var binding: CreditorFormActivityBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.creditor_form_activity)
        setupElements()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
    }

    private fun setupElements() {
        setupToolbar(getString(R.string.creditor_form))
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.creditor = CreditorForm()
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
        val intent = Intent(applicationContext, ContractFormActivity::class.java)
        putPersonalInExtra(intent)
        putVehicleInExtra(intent)
        putCredorInExtra(intent)

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

    private fun putCredorInExtra(intent: Intent) {
        val creditor = binding.creditor
        intent.putExtra(CREDITOR_IDENTIFIER, creditor)
    }

    private fun getVehicleFromBundle() : VehicleForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(VehicleActivity.VEHICLE_IDENTIFIER) as VehicleForm
    }

    private fun getPersonalFromBundle() : PersonalForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as PersonalForm
    }

}
