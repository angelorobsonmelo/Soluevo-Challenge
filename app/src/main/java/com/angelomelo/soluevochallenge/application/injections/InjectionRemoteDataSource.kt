package com.angelomelo.soluevochallenge.application.injections

import com.angelomelo.soluevochallenge.application.injections.InjectionApiDataSource.provideAuthApiDataSource
import com.angelomelo.soluevochallenge.application.injections.InjectionApiDataSource.provideContractApiDataSource
import com.angelomelo.soluevochallenge.service.remote.auth.AuthRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.auth.AuthRemoteDataSourceImpl.Companion.getInstance
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSourceImpl.Companion.getInstance

object InjectionRemoteDataSource {

    @JvmStatic
    fun provideAuthDataSource(): AuthRemoteDataSource {
        return getInstance(provideAuthApiDataSource())
    }

    @JvmStatic
    fun provideContractRemoteDataSource(): ContractRemoteDataSource {
        return getInstance(provideContractApiDataSource())
    }

}