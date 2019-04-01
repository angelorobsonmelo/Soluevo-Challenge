package com.angelomelo.soluevochallenge.application.usecases.remote.contract

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.contract.ContractRemoteDataSource
import java.math.BigInteger

class SaveContractUseCase(private val contractRemoteDataSource: ContractRemoteDataSource) {

    fun saveContract(requestObjectsForm: RequestObjectsForm, callback: UseCase.UseCaseCallback<BigInteger>) {
        contractRemoteDataSource.save(requestObjectsForm, object: BaseRemoteDataSource.RemoteDataSourceCallback<BigInteger> {
            override fun onSuccess(response: BigInteger) {
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