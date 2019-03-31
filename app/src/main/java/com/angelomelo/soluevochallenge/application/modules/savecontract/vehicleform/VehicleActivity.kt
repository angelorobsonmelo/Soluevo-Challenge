package com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.creditorform.CreditorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.databinding.VehicleFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.angelomelo.soluevochallenge.domain.form.VehicleForm
import com.kofigyan.stateprogressbar.StateProgressBar

class VehicleActivity : StateProgressBarBaseActivity() {

    companion object {
        const val VEHICLE_IDENTIFIER = "VEHICLE_IDENTIFIER"
    }

    private lateinit var binding: VehicleFormActivityBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.vehicle_form_activity)
        setupElements()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
    }

    private fun setupElements() {
        setupToolbar(getString(R.string.vehicle_form))
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.vehicle        = VehicleForm()
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                if (validator.validate()) {
                    putObjectsFromTheStepsOfTheFormAndGoToTheCreditorScreen()
                }
            }

            R.id.btnBack -> finish()
        }
    }

    private fun putObjectsFromTheStepsOfTheFormAndGoToTheCreditorScreen() {
        val intent = Intent(applicationContext, CreditorFormActivity::class.java)
        putPersonalInExtra(intent)
        putVehicleInExtra(intent)
        startActivity(intent)
    }

    private fun putPersonalInExtra(intent: Intent) {
        val personal = getPersonalFromBundle()
        intent.putExtra(PersonalFormActivity.PERSONAL_IDENTIFIER, personal)
    }

    private fun putVehicleInExtra(intent: Intent) {
        val vehicle = binding.vehicle
        intent.putExtra(VEHICLE_IDENTIFIER, vehicle)
    }

    private fun getPersonalFromBundle() : PersonalForm {
        val bundle: Bundle? = intent.extras
        return bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as PersonalForm
    }

}
