package com.angelomelo.soluevochallenge.service.remote.attachment

import com.angelomelo.soluevochallenge.domain.Attachment
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigInteger

interface AttachmentApiDataSource {

    @POST("/contracts/file")
    fun save(@Body attachment: Attachment): Observable<Attachment>

    @GET("detran/public/contract_files?{contract_code}")
    fun getAttachments(@Path("contract_code")  contractCode: BigInteger): Observable<List<Attachment>>
}