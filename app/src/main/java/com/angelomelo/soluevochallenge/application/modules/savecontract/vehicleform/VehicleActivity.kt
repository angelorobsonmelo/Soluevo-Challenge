//package com.angelomelo.soluevochallenge.application.modules.savecontract.vehicleform
//
//import android.os.Bundle
//import android.content.Intent
//import android.view.View
//import com.angelomelo.soluevochallenge.R
//import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
//import com.angelomelo.soluevochallenge.application.modules.savecontract.credorform.CredorFormActivity
//import com.kofigyan.stateprogressbar.StateProgressBar
//
//class VehicleActivity : UsageBaseActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vehicle)
//
//        injectBackView()
//        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
//    }
//
//    override fun onClick(v: View) {
//
//        when (v.id) {
//            R.id.btnNext -> {
//                val intent = Intent(applicationContext, CredorFormActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.btnBack -> finish()
//        }
//    }
//
//}
