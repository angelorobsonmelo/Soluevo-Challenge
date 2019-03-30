package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.Attachment

class AttachmentsViewModel : BaseViewModel<Attachment>() {

    private val saveAttachment = InjectionUseCase.provideSaveAttachmentUseCase()

    fun save(attachment: Attachment) {
        saveAttachment.save(attachment, object : UseCase.UseCaseCallback<Attachment> {
            override fun onSuccess(response: Attachment) {
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