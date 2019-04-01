package com.angelomelo.soluevochallenge.application.modules.savecontract

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.request.RequestObjectsForm
import java.math.BigInteger

class SaveContractViewModel : BaseViewModel<BigInteger>() {

    private val getContractsUseCase = InjectionUseCase.provideSaveContractUseCase()

    fun saveContract(requestObjectsForm: RequestObjectsForm) {
        getContractsUseCase.saveContract(requestObjectsForm, object : UseCase.UseCaseCallback<BigInteger> {
            override fun onSuccess(response: BigInteger) {
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