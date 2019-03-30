package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.Attachment

class AttachmentsViewModel : BaseViewModel<Void>() {

    private val saveAttachment = InjectionUseCase.provideSaveAttachmentUseCase()

    fun save(attachment: Attachment) {
        saveAttachment.save(attachment, object : UseCase.VoidUseCaseCallback {
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