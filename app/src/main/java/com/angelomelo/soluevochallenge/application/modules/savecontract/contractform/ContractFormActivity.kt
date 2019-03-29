package com.angelomelo.soluevochallenge.application.modules.savecontract.contractform


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.AttachmentsActivity
import com.angelomelo.soluevochallenge.databinding.ActivityContractFormBinding
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
                    goToattachmentsForm()
                }
            }

            R.id.btnBack -> finish()
        }
    }

    private fun goToattachmentsForm() {
        val intent = Intent(applicationContext, AttachmentsActivity::class.java)
        startActivity(intent)
    }

}
