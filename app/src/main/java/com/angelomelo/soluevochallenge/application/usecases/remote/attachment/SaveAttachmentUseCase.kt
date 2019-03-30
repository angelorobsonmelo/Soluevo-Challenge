package com.angelomelo.soluevochallenge.application.usecases.remote.attachment

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.attachment.AttachmentRemoteDataSource

class SaveAttachmentUseCase(private val attachmentRemoteDataSource: AttachmentRemoteDataSource) {

    fun save(attachment: Attachment, callback: UseCase.UseCaseCallback<Attachment>) {
        attachmentRemoteDataSource.save(attachment, object : BaseRemoteDataSource.RemoteDataSourceCallback<Attachment> {
            override fun onSuccess(response: Attachment) {
                callback.onSuccess(attachment)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun isLoading(isLoading: Boolean) {
                callback.isLoading(isLoading)
            }


        })
    }

}