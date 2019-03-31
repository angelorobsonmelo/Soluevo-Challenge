package com.angelomelo.soluevochallenge.application.modules.contractdetail

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import java.math.BigInteger

class ContractDetailViewModel : BaseViewModel<List<AttachmentResponse>>() {

    private val getAttachmentsUseCase = InjectionUseCase.provideGetAttachmentUseCase()

    fun getAttachments(contractCode: BigInteger) {
        getAttachmentsUseCase.getAttachments(contractCode, object: UseCase.UseCaseCallback<List<AttachmentResponse>>{
            override fun onSuccess(response: List<AttachmentResponse>) {
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
