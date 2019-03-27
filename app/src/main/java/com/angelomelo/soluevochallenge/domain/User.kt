package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("UUID")
    var uuid: String = "",
    @SerializedName("financials_code")
    var financialCode: Int = -1,
    var username: String = "",
    var password: String = ""
)

