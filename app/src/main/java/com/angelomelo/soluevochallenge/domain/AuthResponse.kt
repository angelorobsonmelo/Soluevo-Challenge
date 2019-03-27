package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("FinancialUser")
    val user: User,
    val Token: String)
