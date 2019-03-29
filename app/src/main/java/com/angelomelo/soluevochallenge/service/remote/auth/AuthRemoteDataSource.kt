package com.angelomelo.soluevochallenge.service.remote.auth

import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.domain.User

interface AuthRemoteDataSource {

    fun auth(user: User, callback: BaseRemoteDataSource.VoidRemoteDataSourceCallback)
}