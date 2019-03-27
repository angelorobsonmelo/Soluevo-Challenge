package com.angelomelo.soluevochallenge.service.remote.contract

import com.angelomelo.cm_customer_android.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.domain.Contract

interface ContractRemoteDataSource {

    fun getContracts(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Contract>>)

}