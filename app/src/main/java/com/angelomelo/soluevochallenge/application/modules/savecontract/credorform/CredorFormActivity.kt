package com.angelomelo.soluevochallenge.application.modules.savecontract.credorform

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.databinding.ActivityCredorBinding
import com.angelomelo.soluevochallenge.databinding.PersonalAcitivyBinding
import com.angelomelo.soluevochallenge.domain.request.Credor
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.kofigyan.stateprogressbar.StateProgressBar

class CredorFormActivity : UsageBaseActivity() {

    companion object {
        const val CREDITOR_IDENTIFIER = "CREDITOR_IDENTIFIER"
    }

    private lateinit var binding: ActivityCredorBinding
    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_credor)
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.creditor = Credor()
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btnNext -> {
                if (validator.validate()) {
                      val intent = Intent(applicationContext, ContractFormActivity::class.java)
                     startActivity(intent)
                }

            }

            R.id.btnBack -> finish()
        }
    }

}
