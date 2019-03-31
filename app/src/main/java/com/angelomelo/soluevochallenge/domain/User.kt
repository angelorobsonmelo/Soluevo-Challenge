package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("UUID")
    var uuid: String = "",
    @SerializedName("financials_code")
    var financialCode: Int = 1001,
    var username: String = "",
    var password: String = "",
    @SerializedName("Name")
    var name: String = "",
    @Transient
    var ufDetranPosition: Int = 0,
    var ufDetran: String = ""
)

