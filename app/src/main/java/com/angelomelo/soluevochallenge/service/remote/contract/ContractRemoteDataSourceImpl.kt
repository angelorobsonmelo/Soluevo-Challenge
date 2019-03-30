package com.angelomelo.soluevochallenge.service.remote.contract

import androidx.annotation.NonNull
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import rx.Observable
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

    override fun getContracts(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<ContractResponse>>) {
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

    override fun save(requestObjectsForm: RequestObjectsForm, callback: BaseRemoteDataSource.VoidRemoteDataSourceCallback) {
        val contractObservable = contractApiDataSource.save(requestObjectsForm.contractRequest)
        val vehicleObservalble = contractApiDataSource.save(requestObjectsForm.vehicleRequest)
        val creditorObservable = contractApiDataSource.save(requestObjectsForm.creditorRequest)


        Observable.merge(vehicleObservalble, contractObservable, creditorObservable).subscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .doOnCompleted {
                callback.onSuccess()
            }
            .subscribe(
                {

                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }
}