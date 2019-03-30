package com.angelomelo.soluevochallenge.domain.request

import com.angelomelo.soluevochallenge.application.utils.RandomUtil.getRandomNumber
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
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

data class Data(
    val personal: Personal,
    @SerializedName("veiculo")
    val vehicle: Vehicle,
    @SerializedName("credor")
    val creditor: Creditor,
    @SerializedName("contratos")
    val contracts: Contracts
)

data class Personal(
    val name: String,
    val rg: String
)

data class Vehicle(
    @SerializedName("remarcado")
    val redial: String,
    val renavam: String,
    @SerializedName("uf_placa")
    val ufPlate: String,
    @SerializedName("chassi")
    val chassis: String
)

data class Creditor(
    @SerializedName("endereco")
    val address: String,
    val cep: BigInteger,
    val uf: String,
    @SerializedName("endereco_numero")
    val addressNumber: Int,
    @SerializedName("municipio")
    val county: String,
    @SerializedName("endereco_numero_complemento")
    val addressComplementNumber: String,
    @SerializedName("nome_agente_financeiro_instituicao_financeira")
    val nameFinancialAgentFinancialInstitution: String,
    val cnpj: BigInteger,
    @SerializedName("telefone")
    val telephone: String,
    @SerializedName("bairro")
    val neighborhood: String
)

data class Contracts(
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