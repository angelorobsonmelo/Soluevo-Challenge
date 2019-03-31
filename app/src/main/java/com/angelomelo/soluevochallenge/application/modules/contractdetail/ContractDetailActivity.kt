package com.angelomelo.soluevochallenge.application.modules.contractdetail

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angelomelo.soluevochallenge.R


class ContractDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contract_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContractDetailFragment.newInstance())
                .commitNow()
        }
    }

}
