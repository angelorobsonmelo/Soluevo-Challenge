package com.angelomelo.soluevochallenge.application.usecases.remote.contract

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource

class SaveContractUseCase(private val contractRemoteDataSource: ContractRemoteDataSource) {

    fun saveContract(requestObjectsForm: RequestObjectsForm, callback: UseCase.VoidUseCaseCallback) {
        contractRemoteDataSource.save(requestObjectsForm, object: BaseRemoteDataSource.VoidRemoteDataSourceCallback {
            override fun onSuccess() {
                callback.onSuccess()
            }

            override fun onEmpty() {

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