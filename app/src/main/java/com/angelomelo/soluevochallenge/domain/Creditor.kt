package com.angelomelo.soluevochallenge.domain

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

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