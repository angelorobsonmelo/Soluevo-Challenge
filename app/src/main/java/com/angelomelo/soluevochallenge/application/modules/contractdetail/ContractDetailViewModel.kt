package com.angelomelo.soluevochallenge.application.modules.contractdetail

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.Attachment
import java.math.BigInteger

class ContractDetailViewModel : BaseViewModel<List<Attachment>>() {

    private val getAttachmentsUseCase = InjectionUseCase.provideGetAttachmentUseCase()

    fun getAttachments(contractCode: BigInteger) {
        getAttachmentsUseCase.getAttachments(contractCode, object: UseCase.UseCaseCallback<List<Attachment>>{
            override fun onSuccess(response: List<Attachment>) {
                successObserver.value = response
            }

            override fun onEmptyData() {
                emptyObserver.value = null
            }

            override fun isLoading(isLoading: Boolean) {

            }

            override fun onError(errorDescription: String) {
                errorObserver.value = errorDescription
            }

        })
    }

}
