package com.angelomelo.soluevochallenge.service

import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.domain.AuthResponse

object ServiceUtils {

    @JvmStatic
    fun saveUserAndTokenInSession(authResponse: AuthResponse) {

        SoluevoChallengeApplication.mSessionUseCase?.saveAuthSession(authResponse)
    }
}