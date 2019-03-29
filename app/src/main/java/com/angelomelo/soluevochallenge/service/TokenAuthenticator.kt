package com.angelomelo.soluevochallenge.service


import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator: Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val session = SoluevoChallengeApplication.mSessionUseCase!!.getAuthSession()
        SoluevoChallengeApplication.authUseCase?.reAuth(session?.user!!)
        return response.request().newBuilder().header("Authorization", "Bearer ${session?.Token}").build()
    }


}