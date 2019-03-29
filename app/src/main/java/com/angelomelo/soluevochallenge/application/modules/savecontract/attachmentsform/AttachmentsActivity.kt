package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.databinding.ActivityAttachmentsBinding
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.usage_footer_button_layout.*

class AttachmentsActivity : UsageBaseActivity() {

    companion object {
        const val ATTACHMENTS_IDENTIFIER = "ATTACHMENTS_IDENTIFIER"
    }

    private lateinit var binding: ActivityAttachmentsBinding
    private lateinit var validator: Validator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attachments)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_attachments)
        setupBinding()
        setupValidator()
        injectCommonViews()
        injectBackView()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE)
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
                //Todo salvar tudo aqui
            }

            R.id.btnBack -> finish()
        }
    }

}
