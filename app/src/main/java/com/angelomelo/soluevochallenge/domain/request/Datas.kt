package com.angelomelo.soluevochallenge.domain.request

import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.domain.Creditor
import com.angelomelo.soluevochallenge.domain.Personal
import com.angelomelo.soluevochallenge.domain.Vehicle
import com.google.gson.annotations.SerializedName

data class DataVehicle(
    @SerializedName("veiculo")
    val vehicle: Vehicle
)

data class DataContract(
    val personal: Personal,
    @SerializedName("contrato")
    val contracts: Contract
)

data class DataCreditor(
    val personal: Personal,
    @SerializedName("credor")
    val creditor: Creditor
)