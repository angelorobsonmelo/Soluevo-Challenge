package com.angelomelo.soluevochallenge.application.modules.savecontract

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.kofigyan.stateprogressbar.StateProgressBar

abstract class StateProgressBarBaseActivity : AppCompatActivity(), View.OnClickListener {

     private val personal = SoluevoChallengeApplication.instance.resources.getString(R.string.personal)
     private val vehicle = SoluevoChallengeApplication.instance.resources.getString(R.string.vehicle)
     private val creditor = SoluevoChallengeApplication.instance.resources.getString(R.string.creditor)
     private val contract = SoluevoChallengeApplication.instance.resources.getString(R.string.contract)
     private val attachments = SoluevoChallengeApplication.instance.resources.getString(R.string.attachments)

     private var descriptionData = arrayOf(personal, vehicle, creditor, contract, attachments)

     lateinit var nextBtn: Button
     lateinit var backBtn: Button
     lateinit var stateprogressbar: StateProgressBar


    protected fun setupToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }


}