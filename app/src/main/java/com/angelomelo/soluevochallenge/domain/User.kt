package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("UUID")
    val uuid: String,
    @SerializedName("financials_code")
    val financialCode: Int,
    val name: String,
    val password: String
)

