package com.angelomelo.soluevochallenge.application.modules.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angelomelo.soluevochallenge.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthFragment.newInstance())
                .commitNow()
        }
    }

}
