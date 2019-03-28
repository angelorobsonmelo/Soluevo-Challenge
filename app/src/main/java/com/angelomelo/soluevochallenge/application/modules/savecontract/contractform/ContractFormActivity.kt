//package com.angelomelo.soluevochallenge.application.modules.savecontract.contractform
//
//import android.os.Bundle
//import android.app.Activity
//import android.content.Intent
//import android.view.View
//import com.angelomelo.soluevochallenge.R
//import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
//import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.AttachmentsActivity
//import com.kofigyan.stateprogressbar.StateProgressBar
//
//import kotlinx.android.synthetic.main.activity_contract_form.*
//
//class ContractFormActivity : UsageBaseActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contract_form)
//
//        injectBackView()
//        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
//    }
//
//    override fun onClick(v: View) {
//
//        when (v.id) {
//            R.id.btnNext -> {
//                val intent = Intent(applicationContext, AttachmentsActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.btnBack -> finish()
//        }
//    }
//
//}
