package com.angelomelo.soluevochallenge.application.modules.savecontract

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.databinding.AuthFragmentBinding
import com.kofigyan.stateprogressbar.StateProgressBar

abstract class UsageBaseActivity : AppCompatActivity(), View.OnClickListener {

     var descriptionData = arrayOf("Personal", "Veicúlo", "Credor", "Contrato", "Anexos")
     lateinit var nextBtn: Button
     lateinit var backBtn: Button
     lateinit var stateprogressbar: StateProgressBar

    protected fun injectCommonViews() {
        nextBtn = findViewById(R.id.btnNext)
        nextBtn.setOnClickListener(this)

        stateprogressbar = findViewById(R.id.usage_stateprogressbar)
        stateprogressbar.setStateDescriptionData(descriptionData)
    }

    protected fun injectBackView() {
        backBtn = findViewById(R.id.btnBack)
        backBtn.setOnClickListener(this)
    }


}