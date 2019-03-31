package com.angelomelo.soluevochallenge.application.usecases.remote.attachment

import com.angelomelo.soluevochallenge.application.modules.main.MainFragment
import com.angelomelo.soluevochallenge.application.usecases.UseCase
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
                    val imagesAttachments = MainFragment.getOnlyImageAttachments(response)

                    if (response.isEmpty() || imagesAttachments.isEmpty()) {
                        callback.onEmptyData()
                        return
                    }

                    callback.onSuccess(imagesAttachments)
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