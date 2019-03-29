package com.angelomelo.soluevochallenge.application.usecases.remote.contract

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.domain.request.ContractRequest
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource

class SaveContractUseCase(private val contractRemoteDataSource: ContractRemoteDataSource) {

    fun saveContract(contractRequest: ContractRequest, callback: UseCase.UseCaseCallback<Contract>) {
        contractRemoteDataSource.saveContract(contractRequest, object: BaseRemoteDataSource.RemoteDataSourceCallback<Contract> {

            override fun onSuccess(response: Contract) {
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