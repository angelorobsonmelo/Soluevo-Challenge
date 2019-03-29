package com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.credorform.CredorFormActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.databinding.ActivityVehicleBinding
import com.angelomelo.soluevochallenge.domain.form.ContractForm.VehicleForm
import com.angelomelo.soluevochallenge.domain.request.Personal
import com.kofigyan.stateprogressbar.StateProgressBar

class VehicleActivity : UsageBaseActivity() {

    companion object {
        const val VEHICLE_IDENTIFIER = "VEHICLE_IDENTIFIER"
    }

    private lateinit var binding: ActivityVehicleBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle)
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
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
                    goToCreditorForm()
                }
            }

            R.id.btnBack -> finish()
        }
    }

    private fun goToCreditorForm() {
        val bundle: Bundle? = intent.extras
//        val personal = bundle?.getParcelable(PersonalFormActivity.PERSONAL_IDENTIFIER) as Personal
//        print(personal.name)

        val intent = Intent(applicationContext, CredorFormActivity::class.java)
        startActivity(intent)
    }

}
