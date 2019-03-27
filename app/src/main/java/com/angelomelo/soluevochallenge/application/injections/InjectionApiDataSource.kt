package com.angelomelo.soluevochallenge.application.injections

import br.com.stant.stant_android_occurrences.services.ApiDataSource.Companion.createService
import com.angelomelo.cm_customer_android.service.remote.auth.AuthApiDataSource

object InjectionApiDataSource {

    @JvmStatic
    fun provideAuthApiDataSource(): AuthApiDataSource {
        return createService(AuthApiDataSource::class.java)
    }

}