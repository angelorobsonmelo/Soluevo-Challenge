package com.angelomelo.soluevochallenge.application.modules.savecontract.personalform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.StateProgressBarBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.PersonalFormActivityBinding
import com.angelomelo.soluevochallenge.domain.form.PersonalForm
import com.kofigyan.stateprogressbar.StateProgressBar

class PersonalFormActivity: StateProgressBarBaseActivity() {

    companion object {
        const val PERSONAL_IDENTIFIER = "PERSONAL_IDENTIFIER"
    }

    private lateinit var binding: PersonalFormActivityBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.personal_form_activity)
        setupElements()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
    }

    private fun setupElements() {
        setupToolbar(getString(R.string.personal_form))
        setupBinding()
        setupValidator()
        injectCommonViews()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.personal    = PersonalForm()
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun onClick(v: View) {
        if (validator.validate()) {
            setPersonalInExtraAndGoToVehicleForm()
        }
    }

    private fun setPersonalInExtraAndGoToVehicleForm() {
        val intent = Intent(applicationContext, VehicleActivity::class.java)
        val personal = binding.personal
        intent.putExtra(PERSONAL_IDENTIFIER, personal)
        startActivity(intent)
    }

}