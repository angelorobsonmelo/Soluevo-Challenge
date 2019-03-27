package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class Contract(
    val code: Int,
    val status: Boolean,
    @SerializedName("status_detran")
    val statusDetran: Boolean,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("last_update")
    val lastUpdate: Date
)