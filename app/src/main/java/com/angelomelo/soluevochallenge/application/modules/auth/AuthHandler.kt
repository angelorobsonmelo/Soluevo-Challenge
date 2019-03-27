package com.angelomelo.soluevochallenge.application.modules.auth

import com.angelomelo.soluevochallenge.domain.User

interface AuthHandler {

    fun auth(user: User)
}