package com.angelomelo.soluevochallenge.application.modules.savecontract.personalform

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity
import com.angelomelo.soluevochallenge.databinding.PersonalAcitivyBinding
import com.kofigyan.stateprogressbar.StateProgressBar

class PersonalFormActivity: UsageBaseActivity() {

    private lateinit var binding: PersonalAcitivyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.personal_acitivy)
        injectCommonViews()

        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
    }

    override fun onClick(v: View) {
        /*Intent intent = new Intent(getApplicationContext(), VehicleActivity.class);
        startActivity(intent);*/
    }

}