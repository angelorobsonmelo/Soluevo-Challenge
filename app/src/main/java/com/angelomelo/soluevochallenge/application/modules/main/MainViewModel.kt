package com.angelomelo.soluevochallenge.application.modules.main

import androidx.lifecycle.MutableLiveData
import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.domain.response.ContractResponse

class MainViewModel : BaseViewModel<List<ContractResponse>>() {

    private val getContractsUseCase = InjectionUseCase.provideGetContractsUseCase()
    private val getAttachmentsUseCase = InjectionUseCase.provideGetAttachmentsUseCase()

    val attachmentsOnsuccessObserver   = MutableLiveData<List<AttachmentResponse>>()

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

    fun getAttachments() {
        getAttachmentsUseCase.GetAttachments(object : UseCase.UseCaseCallback<List<AttachmentResponse>> {
            override fun onSuccess(response: List<AttachmentResponse>) {
                attachmentsOnsuccessObserver.value = response
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
