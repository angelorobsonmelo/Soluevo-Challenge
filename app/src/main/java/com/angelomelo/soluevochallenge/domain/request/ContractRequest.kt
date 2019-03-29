package com.angelomelo.soluevochallenge.domain.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
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
   var endereco: String = "",
   var cep: Int = 0,
   @Expose
   var cepField: String = "",
   var uf: String = "",
   @SerializedName("endereco_numero")
   var enderecoNumero: Int  = 0,
   @Expose
   var enderecoNumeroField: String  = "",
   var municipio: String = "",
   @SerializedName("endereco_numero_complemento")
   var enderecoNumeroComplemento: String = "",
   @SerializedName("nome_agente_financeiro_instituicao_financeira")
   var nomeAgenteFinanceiroInstituicaoFinanceira: String = "",
   var cnpj: Int = 0,
   @Expose
   var cnpjField: String = "",
   var telefone: String = "",
   var bairro: String = ""
): Parcelable

@Parcelize
data class Contratos(
    @SerializedName("quantidade_meses")
    var quantidadeMeses: Int = 0,
    var comissao: Int = 0,
    @SerializedName("taxa_mora")
    var taxaMora: Int = 0,
    @SerializedName("valor_taxa_mora")
    var valorTaxaMora: Int = 0,
    @SerializedName("valor_taxa_de_contrato")
    var valorTaxaContrato: Int = 0,
    @SerializedName("valor_juros_ao_ano")
    var valorJurosAno: Int = 0,
    @SerializedName("indicacao_comissao")
    var indicacaoComissao: String = "",
    @SerializedName("taxa_taxa_multa")
    var taxaTaxaMulta: Int = 0,
    @SerializedName("numero_gravame")
    var numeroGravame: Int = 0,
    var numeroGravameField: String = "",
    @SerializedName("tipo_restricao")
    var tipoRestricao: String = "",
    @SerializedName("valor_juros_ao_mes")
    var valorJurosMes: Int = 0,
    var indices: String = ""

): Parcelable