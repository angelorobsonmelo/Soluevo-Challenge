package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.math.BigInteger

data class Contract(
    @SerializedName("data_do_contrato")
    val contractDate: BigInteger,
    @SerializedName("numero_do_contrato")
    val code: String,
    @SerializedName("numero_gravame")
    val tagNumber: Int,
    @SerializedName("quantidade_meses")
    val amountMonths: Int,
    @SerializedName("tipo_restricao")
    val typeRestriction: String,
    @SerializedName("taxa_mora")
    val rateMora: BigDecimal,
    @SerializedName("valor_taxa_mora")
    val valueMoraRate: BigDecimal,
    @SerializedName("taxa_taxa_multa")
    val feeFineRate: BigDecimal,
    @SerializedName("valor_taxa_multa")
    val valueFeeFineRate: BigDecimal,
    @SerializedName("valor_taxa_de_contrato")
    val valueContractRate: BigDecimal,
    @SerializedName("valor_juros_ao_mes")
    val valueInterestMonth: BigDecimal,
    @SerializedName("valor_iof")
    val iofValue: BigDecimal,
    @SerializedName("valor_juros_ao_ano")
    val valueYearInterest: BigDecimal,
    @SerializedName("indices")
    val indexes: String,
    @SerializedName("indicacao_comissao")
    val commissionStatement: String,
    @SerializedName("comissao")
    val commission: BigDecimal,
    @SerializedName("indicacao_penalidade")
    val penaltyIndication: String
)