package com.angelomelo.soluevochallenge.service.remote.auth

import com.angelomelo.soluevochallenge.domain.AuthResponse
import com.angelomelo.soluevochallenge.domain.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiDataSource {

    @POST("/auth/financial")
    fun auth(@Body user: User): Observable<AuthResponse>
}