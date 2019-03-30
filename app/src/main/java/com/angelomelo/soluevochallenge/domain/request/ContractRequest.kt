package com.angelomelo.soluevochallenge.domain.request

import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.domain.Creditor
import com.angelomelo.soluevochallenge.domain.Personal
import com.angelomelo.soluevochallenge.domain.Vehicle
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class ContractRequest(
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

