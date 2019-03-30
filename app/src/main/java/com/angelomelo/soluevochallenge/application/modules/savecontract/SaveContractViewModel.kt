package com.angelomelo.soluevochallenge.application.modules.savecontract

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.angelomelo.soluevochallenge.domain.request.ContractRequest

class SaveContractViewModel : BaseViewModel<ContractResponse>() {

    private val getContractsUseCase = InjectionUseCase.provideSaveContractUseCase()

    fun saveContract(contractRequest: ContractRequest) {
        getContractsUseCase.saveContract(contractRequest, object : UseCase.UseCaseCallback<ContractResponse> {
            override fun onSuccess(response: ContractResponse) {
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