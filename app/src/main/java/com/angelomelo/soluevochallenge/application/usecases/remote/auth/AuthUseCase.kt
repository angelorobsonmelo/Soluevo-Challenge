package com.angelomelo.soluevochallenge.application.usecases.remote.auth


import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource.VoidRemoteDataSourceCallback
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.User
import com.angelomelo.soluevochallenge.service.remote.auth.AuthRemoteDataSource

class AuthUseCase(private val authRemoteDataSource: AuthRemoteDataSource) {

    fun auth(user: User, callback: UseCase.VoidUseCaseCallback) {
        authRemoteDataSource.auth(user, object : VoidRemoteDataSourceCallback {

            override fun onSuccess() {
                callback.onSuccess()
            }

            override fun onEmpty() {
                callback.onEmptyData()
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun isLoading(isLoading: Boolean) {
                callback.isLoading(isLoading)
            }

        })
    }

    fun reAuth(user: User) {
        authRemoteDataSource.auth(user, object : VoidRemoteDataSourceCallback {

            override fun onSuccess() {

            }

            override fun onEmpty() {

            }

            override fun onError(errorMessage: String) {

            }

            override fun isLoading(isLoading: Boolean) {

            }

        })
    }

}