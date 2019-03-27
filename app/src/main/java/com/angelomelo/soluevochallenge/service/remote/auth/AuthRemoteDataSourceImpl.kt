package com.angelomelo.soluevochallenge.service.remote.auth

import androidx.annotation.NonNull
import com.angelomelo.cm_customer_android.service.BaseRemoteDataSource
import com.angelomelo.cm_customer_android.service.remote.auth.AuthApiDataSource
import com.angelomelo.soluevochallenge.domain.User
import com.angelomelo.soluevochallenge.service.ServiceUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AuthRemoteDataSourceImpl(private val mAuthApiDataSource: AuthApiDataSource) : AuthRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: AuthRemoteDataSourceImpl? = null

        fun getInstance(@NonNull mAuthApiDataSource: AuthApiDataSource): AuthRemoteDataSourceImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthRemoteDataSourceImpl(mAuthApiDataSource).also {
                    INSTANCE = it
                }
            }
    }

    override fun auth(user: User, callback: BaseRemoteDataSource.VoidRemoteDataSourceCallback) {
        mAuthApiDataSource.auth(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    ServiceUtils.saveUserAndTokenInSession(it)
                    callback.onSuccess()
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }
}