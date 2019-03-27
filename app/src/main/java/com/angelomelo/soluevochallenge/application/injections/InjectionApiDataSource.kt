package com.angelomelo.soluevochallenge.application.injections

import br.com.stant.stant_android_occurrences.services.ApiDataSource.Companion.createService
import com.angelomelo.cm_customer_android.service.remote.auth.AuthApiDataSource
import com.angelomelo.soluevochallenge.service.remote.contract.ContractApiDataSource

object InjectionApiDataSource {

    @JvmStatic
    fun provideAuthApiDataSource(): AuthApiDataSource {
        return createService(AuthApiDataSource::class.java)
    }

    @JvmStatic
    fun provideContractApiDataSource(): ContractApiDataSource {
        return createService(ContractApiDataSource::class.java)
    }

}