package com.angelomelo.soluevochallenge.domain.request

import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.google.gson.annotations.SerializedName

data class ContractRequest(
    @SerializedName("endusers_document")
    val endusersDocument: String = "35507907838",
    val code: String,
    @SerializedName("financial_users_uuid")
    val financialUsersUuid: String = SoluevoChallengeApplication.mSessionUseCase?.getUserInSession()?.uuid!!,
    val data: Data
)

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
    var name: String = "",
    var rg: String = ""
)

data class Vehicle(
    @SerializedName("remarcado")
    var redial: String = "",
    var renavam: String = "",
    @SerializedName("uf_placa")
    var ufPlate: String = "",
    @SerializedName("chassi")
    var chassis: String = ""
)

data class Creditor(
    @SerializedName("endereco")
    var address: String = "",
    var cep: Int = 0,
    var uf: String = "",
    @SerializedName("endereco_numero")
    var addressNumber: Int = 0,
    @SerializedName("municipio")
    var county: String = "",
    @SerializedName("endereco_numero_complemento")
    var addressComplementNumber: String = "",
    @SerializedName("nome_agente_financeiro_instituicao_financeira")
    var nameFinancialAgentFinancialInstitution: String = "",
    var cnpj: Int = 0,
    @SerializedName("telefone")
    var telephone: String = "",
    @SerializedName("bairro")
    var neighborhood: String = ""
)

data class Contracts(
    @SerializedName("quantidade_meses")
    var amountMonths: Int = 0,
    @SerializedName("comissao")
    var commission: Int = 0,
    @SerializedName("taxa_mora")
    var rateMore: Int = 0,
    @SerializedName("valor_taxa_mora")
    var valueMoraRate: Int = 0,
    @SerializedName("valor_taxa_de_contrato")
    var valueContractRate: Int = 0,
    @SerializedName("valor_juros_ao_ano")
    var valueYearInterest: Int = 0,
    @SerializedName("indicacao_comissao")
    var commissionStatement: String = "",
    @SerializedName("taxa_taxa_multa")
    var feeFineRate: Int = 0,
    @SerializedName("numero_gravame")
    var tagNumber: Int = 0,
    @SerializedName("tipo_restricao")
    var typeRestriction: String = "",
    @SerializedName("valor_juros_ao_mes")
    var valueInterestMonth: Int = 0,
    @SerializedName("indices")
    var indexes: String = ""
)