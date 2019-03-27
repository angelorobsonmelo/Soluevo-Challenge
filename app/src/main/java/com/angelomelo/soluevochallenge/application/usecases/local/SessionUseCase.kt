package com.angelomelo.soluevochallenge.application.usecases.local

import com.angelomelo.soluevochallenge.service.local.session.SessionLocalDataSource
import com.angelomelo.soluevochallenge.domain.AuthResponse
import com.angelomelo.soluevochallenge.domain.User

class SessionUseCase(private val mSessionLocalDataSource: SessionLocalDataSource) {

    fun saveAuthSession(authResponse: AuthResponse) {
        mSessionLocalDataSource.saveAuthSession(authResponse)
    }

    fun getAuthSession(): AuthResponse {
        return mSessionLocalDataSource.getAuthSession()
    }

    fun saveUserInSession(user: User) {
        mSessionLocalDataSource.saveUserInSession(user)
    }

    fun getUserInSession(): User {
        return mSessionLocalDataSource.getUserInSession()
    }

    fun destroySession(): Boolean {
       return mSessionLocalDataSource.destroySession()
    }

    fun isLogged(): Boolean {
      return mSessionLocalDataSource.isLogged()
    }

}