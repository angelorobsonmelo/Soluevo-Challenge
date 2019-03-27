package com.angelomelo.soluevochallenge.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Contract(
    val code: Int,
    val status: Boolean,
    @SerializedName("status_detran")
    val statusDetran: Boolean,

    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("last_update")
    val lastUpdate: Date
) : Parcelable