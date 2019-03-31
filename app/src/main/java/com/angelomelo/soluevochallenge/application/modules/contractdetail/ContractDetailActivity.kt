package com.angelomelo.soluevochallenge.application.modules.contractdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angelomelo.soluevochallenge.R


class ContractDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contract_detail_activity)
        if (savedInstanceState == null) {
            val fragment = ContractDetailFragment.newInstance()
            fragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
