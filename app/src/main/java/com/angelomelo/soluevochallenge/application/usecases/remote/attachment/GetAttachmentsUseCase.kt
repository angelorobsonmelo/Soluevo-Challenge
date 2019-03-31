package com.angelomelo.soluevochallenge.application.usecases.remote.attachment

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.attachment.AttachmentRemoteDataSource
import java.math.BigInteger

class GetAttachmentsUseCase(private val attachmentRemoteDataSource: AttachmentRemoteDataSource) {

    fun getAttachments(contractCode: BigInteger, callback: UseCase.UseCaseCallback<List<AttachmentResponse>>) {
        attachmentRemoteDataSource.getAttachments(contractCode, object : BaseRemoteDataSource.RemoteDataSourceCallback<List<AttachmentResponse>> {
            override fun onSuccess(response: List<AttachmentResponse>) {
                callback.onSuccess(response)
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