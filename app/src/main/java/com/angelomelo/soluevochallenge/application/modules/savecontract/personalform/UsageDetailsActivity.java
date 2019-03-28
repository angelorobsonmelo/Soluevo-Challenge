package com.angelomelo.soluevochallenge.application.modules.savecontract.personalform;

import android.os.Bundle;
import android.view.View;
import com.angelomelo.soluevochallenge.R;
import com.angelomelo.soluevochallenge.application.modules.savecontract.UsageBaseActivity;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class UsageDetailsActivity extends UsageBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_acitivy);

        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(getApplicationContext(), UsagePricingActivity.class);
//        startActivity(intent);
    }

}

