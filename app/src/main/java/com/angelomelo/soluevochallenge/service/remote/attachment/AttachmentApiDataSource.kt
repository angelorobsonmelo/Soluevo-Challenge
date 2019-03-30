package com.angelomelo.soluevochallenge.service.remote.attachment

import com.angelomelo.soluevochallenge.domain.Attachment
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AttachmentApiDataSource {

    @POST("/contracts/file")
    fun save(@Body attachment: Attachment): Observable<Attachment>
}