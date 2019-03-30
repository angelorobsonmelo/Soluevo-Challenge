package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Vehicle(
    @SerializedName("remarcado")
    val redial: String,
    val renavam: BigInteger,
    @SerializedName("uf_placa")
    val ufPlate: String,
    @SerializedName("chassi")
    val chassis: String
)