package com.angelomelo.soluevochallenge.application.modules.savecontract

import android.app.Activity
import android.view.View
import android.widget.Button
import com.angelomelo.soluevochallenge.R
import com.kofigyan.stateprogressbar.StateProgressBar

abstract class UsageBaseActivity : Activity(), View.OnClickListener {

     var descriptionData = arrayOf("Personal", "Veicúlo", "Credor", "Contratos", "Anexos")
     lateinit var nextBtn: Button
     lateinit var backBtn: Button
     lateinit var stateprogressbar: StateProgressBar

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        injectCommonViews()
    }


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