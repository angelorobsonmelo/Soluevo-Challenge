package com.angelomelo.soluevochallenge.service.remote.contract

import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.domain.request.ContractRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface ContractApiDataSource {

    @GET("/detran/public/contracts")
    fun getContracts() : Observable<List<ContractResponse>>

    @POST("/detran/public/contracts")
    fun saveContract(@Body contractRequest: ContractRequest): Observable<ContractResponse>
}