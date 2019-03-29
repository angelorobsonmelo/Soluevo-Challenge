package com.angelomelo.soluevochallenge.application

import android.app.Application
import android.content.Intent
import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.local.SessionUseCase
import com.angelomelo.soluevochallenge.application.modules.main.MainActivity
import com.angelomelo.soluevochallenge.application.usecases.remote.auth.AuthUseCase


class SoluevoChallengeApplication: Application() {

    companion object {
       var mSessionUseCase: SessionUseCase? = null
       var authUseCase: AuthUseCase? = null
        lateinit var instance: SoluevoChallengeApplication
    }

    override fun onCreate() {
        super.onCreate()
        mSessionUseCase = InjectionUseCase.provideSessionUseCase(applicationContext)
        authUseCase = InjectionUseCase.provideAuthseCase()

        if (mSessionUseCase!!.isLogged()) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        instance = this
    }

}