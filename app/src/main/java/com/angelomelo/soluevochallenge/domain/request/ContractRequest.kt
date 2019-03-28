package com.angelomelo.soluevochallenge.domain.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ContractRequest(
    @SerializedName("endusers_document")
    val endusersDocument: String = "35507907838",
    val code: String,
    @SerializedName("financial_users_uuid")
    val financialUsersUuid: String,
    val data: Data
)

data class Data(
    val personal: Personal,
    @SerializedName("veiculo")
    val vehicle: Vehicle,
    val credor: Credor,
    val contratos: Contratos
)

@Parcelize
data class Personal(
    var name: String = "",
    var rg: String = ""
) : Parcelable

@Parcelize
data class Vehicle(
   var remarcado: String = "",
   var renavam: String = "",
   @SerializedName("uf_placa")
   var ufPlaca: String = "",
   var chassi: String = ""
) : Parcelable

@Parcelize
data class Credor(
   var endereco: String,
   var cep: Int,
   var uf: String,
   @SerializedName("endereco_numero")
   var enderecoNumero: String,
   var municipio: String,
   @SerializedName("endereco_numero_complemento")
   var enderecoNumeroComplemento: String,
   @SerializedName("nome_agente_financeiro_instituicao_financeira")
   var nomeAgenteFinanceiroInstituicaoFinanceira: String,
   var cnpj: Int,
   var telefone: String,
   var bairro: String
): Parcelable

@Parcelize
data class Contratos(
    @SerializedName("quantidade_meses")
    var quantidadeMeses: Int,
    var comissao: Int,
    @SerializedName("taxa_mora")
    var taxaMora: Int,
    @SerializedName("valor_taxa_mora")
    var valorTaxaMora: Int,
    @SerializedName("valor_taxa_de_contrato")
    var valorTaxaContrato: Int,
    @SerializedName("valor_juros_ao_ano")
    var valorJurosAno: Int,
    @SerializedName("indicacao_comissao")
    var indicacaoComissao: String,
    @SerializedName("taxa_taxa_multa")
    var taxaTaxaMulta: Int,
    @SerializedName("numero_gravame")
    var numeroGravame: Int,
    @SerializedName("tipo_restricao")
    var tipoRestricao: String,
    @SerializedName("valor_juros_ao_mes")
    var valorJurosMes: Int,
    var indices: String

): Parcelable