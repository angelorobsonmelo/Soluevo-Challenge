package com.angelomelo.cm_customer_android.application.injections

import android.content.Context
import com.angelomelo.soluevochallenge.service.local.session.SessionLocalDataSource
import com.angelomelo.soluevochallenge.service.local.session.SessionLocalDataSourceImpl

object InjectionLocalDataSource {

    @JvmStatic
    fun provideSessionLocalDataSource(context: Context): SessionLocalDataSource {
        return SessionLocalDataSourceImpl(context)
    }
}