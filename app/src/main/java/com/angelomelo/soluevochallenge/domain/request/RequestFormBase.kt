package com.angelomelo.soluevochallenge.domain.request

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class RequestFormBase(
    @SerializedName("endusers_document")
    val endusersDocument: String,
    val code: BigInteger,
    val data: String,
    @SerializedName("financial_users_uuid")
    val financialUsersUuid: String
) {
    constructor(data: String, financialUsersUuid: String, contractCode: BigInteger) : this(
        "35507907838",
        contractCode,
        data,
        financialUsersUuid

    )
}

