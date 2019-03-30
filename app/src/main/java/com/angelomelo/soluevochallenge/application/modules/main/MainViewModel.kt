package com.angelomelo.soluevochallenge.application.modules.main

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.response.ContractResponse

class MainViewModel : BaseViewModel<List<ContractResponse>>() {

    private val getContractsUseCase = InjectionUseCase.provideGetContractsUseCase()

    init {
        getContracts()
    }

    fun getContracts() {
        getContractsUseCase.getContracts(object : UseCase.UseCaseCallback<List<ContractResponse>> {
            override fun onSuccess(response: List<ContractResponse>) {
                successObserver.value = response
            }

            override fun onEmptyData() {

            }

            override fun isLoading(isLoading: Boolean) {
                isLoadingObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                errorObserver.value = errorDescription
            }

        })
    }


}
