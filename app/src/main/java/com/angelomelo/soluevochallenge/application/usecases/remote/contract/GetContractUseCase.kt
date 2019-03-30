package com.angelomelo.soluevochallenge.application.usecases.remote.contract

import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource

class GetContractUseCase(private val contractRemoteDataSource: ContractRemoteDataSource) {

    fun getContracts(callback: UseCase.UseCaseCallback<List<ContractResponse>>) {
        contractRemoteDataSource.getContracts(object: BaseRemoteDataSource.RemoteDataSourceCallback<List<ContractResponse>> {

            override fun onSuccess(response: List<ContractResponse>) {
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