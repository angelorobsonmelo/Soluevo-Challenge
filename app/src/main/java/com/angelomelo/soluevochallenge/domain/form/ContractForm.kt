package com.angelomelo.soluevochallenge.domain.form

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ContractForm {

    @Parcelize
    data class PersonalForm(
        var name: String = "",
        var rg: String = ""
    ) : Parcelable

    @Parcelize
    data class VehicleForm(
        var redial: String = "",
        var renavam: String = "",
        var ufPlate: String = "",
        var chassis: String = ""
    ) : Parcelable

    @Parcelize
    data class CreditorForm(
        var address: String = "",
        var cep: String = "",
        var uf: String = "",
        var addressNumber: String = "",
        var county: String = "",
        var addressComplementNumber: String = "",
        var nameFinancialAgentFinancialInstitution: String = "",
        var cnpj: String = "",
        var telephone: String = "",
        var neighborhood: String = ""
    ): Parcelable

    @Parcelize
    data class ContractsForm(
        var amountMonths: String = "",
        var commission: String = "",
        var rateMora: String = "",
        var valueMoraRate: String = "",
        var valueContractRate: String = "",
        var valueYearInterest: String = "",
        var commissionStatement: String = "",
        var feeFineRate: String = "",
        var tagNumber: String = "",
        var typeRestriction: String = "",
        var valueInterestMonth: String = "",
        var indexes: String = ""

    ): Parcelable
}