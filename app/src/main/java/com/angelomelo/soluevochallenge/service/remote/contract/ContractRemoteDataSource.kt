package com.angelomelo.soluevochallenge.service.remote.contract

import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.domain.request.ContractRequest

interface ContractRemoteDataSource {

    fun getContracts(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Contract>>)
    fun saveContract(contractRequest: ContractRequest, callback: BaseRemoteDataSource.RemoteDataSourceCallback<Contract>)

}