package com.angelomelo.soluevochallenge.application.modules.savecontract

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm

class SaveContractViewModel : BaseViewModel<Void>() {

    private val getContractsUseCase = InjectionUseCase.provideSaveContractUseCase()

    fun saveContract(requestObjectsForm: RequestObjectsForm) {
        getContractsUseCase.saveContract(requestObjectsForm, object : UseCase.VoidUseCaseCallback {
            override fun onSuccess() {
                successObserver.value = null
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