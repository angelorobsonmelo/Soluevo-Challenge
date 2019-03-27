package com.angelomelo.soluevochallenge.service.local.session

import com.angelomelo.soluevochallenge.domain.AuthResponse
import com.angelomelo.soluevochallenge.domain.User

interface SessionLocalDataSource {

    fun saveAuthSession(authResponse: AuthResponse)
    fun getAuthSession(): AuthResponse
    fun saveUserInSession(user: User)
    fun getUserInSession(): User
    fun destroySession(): Boolean
    fun isLogged(): Boolean
}