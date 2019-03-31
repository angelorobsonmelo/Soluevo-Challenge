package com.angelomelo.soluevochallenge.service.remote.attachment

import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import java.math.BigInteger

interface AttachmentRemoteDataSource {

    fun save(attachment: Attachment, callback: BaseRemoteDataSource.RemoteDataSourceCallback<Attachment>)
    fun getAttachments(contractCode: BigInteger, callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<AttachmentResponse>>)

}