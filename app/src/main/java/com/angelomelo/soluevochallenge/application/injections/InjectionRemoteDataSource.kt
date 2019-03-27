package com.angelomelo.soluevochallenge.application.injections

import com.angelomelo.soluevochallenge.application.injections.InjectionApiDataSource.provideAuthApiDataSource
import com.angelomelo.soluevochallenge.service.remote.auth.AuthRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.auth.AuthRemoteDataSourceImpl.Companion.getInstance

object InjectionRemoteDataSource {

    @JvmStatic
    fun provideAuthDataSource(): AuthRemoteDataSource {
        return getInstance(provideAuthApiDataSource())
    }

}