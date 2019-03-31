package com.angelomelo.soluevochallenge.service.remote.attachment

import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import io.reactivex.Observable
import retrofit2.http.*
import java.math.BigInteger

interface AttachmentApiDataSource {

    @POST("/contracts/file")
    fun save(@Body attachment: Attachment): Observable<Attachment>

    @GET("detran/public/contract_files")
    fun getAttachments(@Query("contract_code")  contractCode: BigInteger): Observable<List<AttachmentResponse>>
}