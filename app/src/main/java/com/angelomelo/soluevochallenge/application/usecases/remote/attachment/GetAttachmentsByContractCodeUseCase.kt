package com.angelomelo.soluevochallenge.application.usecases.remote.attachment

import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.extensions.getFileName
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import com.angelomelo.soluevochallenge.service.remote.attachment.AttachmentRemoteDataSource
import java.math.BigInteger

class GetAttachmentsByContractCodeUseCase(private val attachmentRemoteDataSource: AttachmentRemoteDataSource) {

    fun getAttachmentsBy(contractCode: BigInteger, callback: UseCase.UseCaseCallback<List<AttachmentResponse>>) {
        attachmentRemoteDataSource.getAttachmentsBy(
            contractCode,
            object : BaseRemoteDataSource.RemoteDataSourceCallback<List<AttachmentResponse>> {
                override fun onSuccess(response: List<AttachmentResponse>) {
                    val newAttachmentResponseFromFilter = getAttachmentResponseFromFilter(response)

                    if (response.isEmpty() || newAttachmentResponseFromFilter.isEmpty()) {
                        callback.onEmptyData()
                        return
                    }

                    callback.onSuccess(newAttachmentResponseFromFilter)
                }

                override fun onError(errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun isLoading(isLoading: Boolean) {
                    callback.isLoading(isLoading)
                }
            })
    }

    private fun getAttachmentResponseFromFilter(response: List<AttachmentResponse>): List<AttachmentResponse> {
        return response.filter {
            it.fileName.getFileName().toLowerCase() == "png" ||
                    it.fileName.getFileName().toLowerCase() == "jpg" ||
                    it.fileName.getFileName() == "JPEG"
        }.map {
            it.urlPath = "http://159.65.244.68/assets/${it.fileName}"
            it
        }
    }

}