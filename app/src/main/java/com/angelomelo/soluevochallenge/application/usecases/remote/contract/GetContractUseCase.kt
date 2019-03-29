package com.angelomelo.soluevochallenge.application.usecases.remote.contract

import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource

class GetContractUseCase(private val contractRemoteDataSource: ContractRemoteDataSource) {

    fun getContracts(callback: UseCase.UseCaseCallback<List<Contract>>) {
        contractRemoteDataSource.getContracts(object: BaseRemoteDataSource.RemoteDataSourceCallback<List<Contract>> {

            override fun onSuccess(response: List<Contract>) {
                callback.onSuccess(response)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun isLoading(isLoading: Boolean) {
                callback.isLoading(isLoading)
            }

        })
    }

}