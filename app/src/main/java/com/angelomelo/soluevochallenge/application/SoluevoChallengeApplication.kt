package com.angelomelo.soluevochallenge.application

import android.app.Application
import android.content.Intent
import com.angelomelo.cm_customer_android.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.local.SessionUseCase
import com.angelomelo.soluevochallenge.application.modules.main.MainActivity


class SoluevoChallengeApplication: Application() {

    companion object {
       var mSessionUseCase: SessionUseCase? = null
    }

    override fun onCreate() {
        super.onCreate()
        mSessionUseCase = InjectionUseCase.provideSessionUseCase(applicationContext)

        if (mSessionUseCase!!.isLogged()) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

}