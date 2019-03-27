package com.angelomelo.cm_customer_android.application.injections

import android.content.Context
import com.angelomelo.soluevochallenge.application.injections.InjectionRemoteDataSource
import com.angelomelo.soluevochallenge.application.usecases.local.SessionUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.auth.AuthUseCase

object InjectionUseCase {

    private val mAuthRemoteDataSource = InjectionRemoteDataSource.provideAuthDataSource()

    @JvmStatic
    fun provideAuthseCase(): AuthUseCase {
        return AuthUseCase(mAuthRemoteDataSource)
    }

    @JvmStatic
    fun provideSessionUseCase(context: Context): SessionUseCase {
        val sessionLocalDataSource = InjectionLocalDataSource.provideSessionLocalDataSource(context)
        return SessionUseCase(sessionLocalDataSource)
    }


}