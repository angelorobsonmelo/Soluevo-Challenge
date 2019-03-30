package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Attachment(
    @SerializedName("contract_code")
    val contractCode: BigInteger,
    @SerializedName("fileext")
    val fileExtension: String,
    @SerializedName("filename")
    val fileName: String,
    @SerializedName("filecontent")
    val fileContent: String,
    @Expose
    val path: String
)