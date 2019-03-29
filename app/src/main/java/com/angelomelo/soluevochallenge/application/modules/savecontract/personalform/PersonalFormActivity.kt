package com.angelomelo.soluevochallenge.application.modules.savecontract.personalform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform.VehicleActivity
import com.angelomelo.soluevochallenge.databinding.PersonalAcitivyBinding
import com.angelomelo.soluevochallenge.domain.form.ContractForm.PersonalForm
import com.kofigyan.stateprogressbar.StateProgressBar

class PersonalFormActivity: UsageBaseActivity() {

    companion object {
        const val PERSONAL_IDENTIFIER = "PERSONAL_IDENTIFIER"
    }

    private lateinit var binding: PersonalAcitivyBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.personal_acitivy)
        setupBinding()
        setupValidator()
        injectCommonViews()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
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
            setPersonalInBundleAndGoToVehicleForm()
        }
    }

    private fun setPersonalInBundleAndGoToVehicleForm() {
        val intent = Intent(applicationContext, VehicleActivity::class.java)
        val personal = binding.personal
        intent.putExtra(PERSONAL_IDENTIFIER, personal)
        startActivity(intent)
    }

}