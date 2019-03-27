package com.angelomelo.soluevochallenge.service.remote.contract

import com.angelomelo.soluevochallenge.domain.Contract
import retrofit2.http.GET
import rx.Observable

interface ContractApiDataSource {

    @GET("/detran/public/contracts")
    fun getContracts() : Observable<List<Contract>>
}