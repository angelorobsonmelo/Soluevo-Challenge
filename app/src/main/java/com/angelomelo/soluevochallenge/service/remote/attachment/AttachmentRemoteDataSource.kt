package com.angelomelo.soluevochallenge.service.remote.attachment

import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource

interface AttachmentRemoteDataSource {

    fun save(attachment: Attachment, callback: BaseRemoteDataSource.VoidRemoteDataSourceCallback)

}