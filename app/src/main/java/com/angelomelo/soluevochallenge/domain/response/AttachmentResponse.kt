package com.angelomelo.soluevochallenge.domain.response

import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import java.util.*

data class AttachmentResponse (
      val uuid: String,
      @SerializedName("contract_code")
      val contractCode: BigInteger,
      @SerializedName("filename")
      val fileName: String,
      @SerializedName("created_at")
      val createdAt: Date,
      @SerializedName("last_update")
      val lastUpdate: Date
)