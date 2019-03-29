package com.angelomelo.soluevochallenge.service.remote.contract

import androidx.annotation.NonNull
import com.angelomelo.soluevochallenge.domain.Contract
import com.angelomelo.soluevochallenge.domain.request.ContractRequest
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ContractRemoteDataSourceImpl(private val contractApiDataSource: ContractApiDataSource): ContractRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: ContractRemoteDataSourceImpl? = null

        fun getInstance(@NonNull contractApiDataSource: ContractApiDataSource): ContractRemoteDataSourceImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ContractRemoteDataSourceImpl(contractApiDataSource).also {
                    INSTANCE = it
                }
            }
    }

    override fun getContracts(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Contract>>) {
        contractApiDataSource.getContracts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    callback.onSuccess(it)
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }

    override fun saveContract(contractRequest: ContractRequest, callback: BaseRemoteDataSource.RemoteDataSourceCallback<Contract>) {
        contractApiDataSource.saveContract(contractRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    callback.onSuccess(it)
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }
}