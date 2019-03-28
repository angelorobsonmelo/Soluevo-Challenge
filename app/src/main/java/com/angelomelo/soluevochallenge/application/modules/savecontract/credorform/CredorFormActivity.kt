package com.angelomelo.soluevochallenge.application.modules.savecontract.credorform

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.application.modules.savecontract.contractform.ContractFormActivity
import com.kofigyan.stateprogressbar.StateProgressBar

class CredorFormActivity : UsageBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credor)

        injectBackView()
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btnNext -> {
                val intent = Intent(applicationContext, ContractFormActivity::class.java)
                startActivity(intent)
            }

            R.id.btnBack -> finish()
        }
    }

}
