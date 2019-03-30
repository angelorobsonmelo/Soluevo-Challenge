package com.angelomelo.soluevochallenge.service.remote.contract

import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource

interface ContractRemoteDataSource {

    fun getContracts(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<ContractResponse>>)
    fun save(requestObjectsForm: RequestObjectsForm, callback: BaseRemoteDataSource.RemoteDataSourceCallback<ContractResponse>)

}