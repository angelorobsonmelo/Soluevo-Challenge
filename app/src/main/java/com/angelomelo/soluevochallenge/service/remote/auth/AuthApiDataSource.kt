package com.angelomelo.cm_customer_android.service.remote.auth

import com.angelomelo.soluevochallenge.domain.AuthResponse
import com.angelomelo.soluevochallenge.domain.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface AuthApiDataSource {

    @POST("/api/v1/auth/sign_in")
    fun auth(@Body user: User): Observable<AuthResponse>
}