package com.angelomelo.soluevochallenge.application.injections

import android.content.Context
import com.angelomelo.soluevochallenge.application.usecases.local.SessionUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.auth.AuthUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.contract.GetContractUseCase

object InjectionUseCase {

    private val mAuthRemoteDataSource    = InjectionRemoteDataSource.provideAuthDataSource()
    private val contractRemoteDataSource = InjectionRemoteDataSource.provideContractRemoteDataSource()

    @JvmStatic
    fun provideAuthseCase(): AuthUseCase {
        return AuthUseCase(mAuthRemoteDataSource)
    }

    @JvmStatic
    fun provideSessionUseCase(context: Context): SessionUseCase {
        val sessionLocalDataSource = InjectionLocalDataSource.provideSessionLocalDataSource(context)
        return SessionUseCase(sessionLocalDataSource)
    }

    @JvmStatic
    fun provideGetContractsUseCase(): GetContractUseCase {
        return GetContractUseCase(contractRemoteDataSource)
    }


}