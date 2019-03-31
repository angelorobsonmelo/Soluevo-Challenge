package com.angelomelo.soluevochallenge.service.remote.attachment

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import com.angelomelo.soluevochallenge.domain.Attachment
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.service.BaseRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigInteger

class AttachmentRemoteDataSourceImpl(private val mAttachmentApiDataSource: AttachmentApiDataSource) :
    AttachmentRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: AttachmentRemoteDataSourceImpl? = null

        fun getInstance(@NonNull mAttachmentApiDataSource: AttachmentApiDataSource): AttachmentRemoteDataSourceImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AttachmentRemoteDataSourceImpl(mAttachmentApiDataSource).also {
                    INSTANCE = it
                }
            }
    }

    @SuppressLint("CheckResult")
    override fun save(attachment: Attachment, callback: BaseRemoteDataSource.RemoteDataSourceCallback<Attachment>) {
        mAttachmentApiDataSource.save(attachment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    callback.onSuccess(attachment)
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun getAttachments(
        contractCode: BigInteger,
        callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<AttachmentResponse>>
    ) {
        mAttachmentApiDataSource.getAttachments(contractCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    callback.onSuccess(it)
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )

    }

}