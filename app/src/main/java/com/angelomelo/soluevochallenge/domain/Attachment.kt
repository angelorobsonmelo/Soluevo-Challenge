package com.angelomelo.soluevochallenge.domain

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Attachment(
    @SerializedName("contract_code")
    val contractCode: BigInteger,
    @SerializedName("fileext")
    val fileExtension: String,
    val fileName: String,
    val fileContent: Bitmap,
    @Expose
    val path: String
)