package com.angelomelo.soluevochallenge.domain.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ContractResponse(
    val code: Int,
    val status: Boolean,
    @SerializedName("status_detran")
    val statusDetran: Boolean,

    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("last_update")
    val lastUpdate: Date
) : Parcelable