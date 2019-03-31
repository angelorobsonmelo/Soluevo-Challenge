package com.angelomelo.soluevochallenge.application.injections

import android.content.Context
import com.angelomelo.soluevochallenge.application.usecases.local.SessionUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.attachment.GetAttachments
import com.angelomelo.soluevochallenge.application.usecases.remote.attachment.GetAttachmentsByContractCodeUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.attachment.SaveAttachmentUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.auth.AuthUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.contract.GetContractUseCase
import com.angelomelo.soluevochallenge.application.usecases.remote.contract.SaveContractUseCase

object InjectionUseCase {

    private val mAuthRemoteDataSource      = InjectionRemoteDataSource.provideAuthDataSource()
    private val contractRemoteDataSource   = InjectionRemoteDataSource.provideContractRemoteDataSource()
    private val attachmentRemoteDataSource = InjectionRemoteDataSource.provideAttachmentRemoteDataSource()

    @JvmStatic
    fun provideAuthseCase(): AuthUseCase {
        return AuthUseCase(mAuthRemoteDataSource)
    }

    @JvmStatic
    fun provideSessionUseCase(context: Context): SessionUseCase {
        val sessionLocalDataSource = InjectionLocalDataSource.provideSessionLocalDataSource(context)
        return SessionUseCase(sessionLocalDataSource)
    }

    @JvmStatic
    fun provideGetContractsUseCase(): GetContractUseCase {
        return GetContractUseCase(contractRemoteDataSource)
    }

    @JvmStatic
    fun provideSaveContractUseCase(): SaveContractUseCase {
        return SaveContractUseCase(contractRemoteDataSource)
    }

    @JvmStatic
    fun provideSaveAttachmentUseCase(): SaveAttachmentUseCase {
        return SaveAttachmentUseCase(attachmentRemoteDataSource)
    }

    @JvmStatic
    fun provideGetAttachmentByContractCodeUseCase(): GetAttachmentsByContractCodeUseCase {
        return GetAttachmentsByContractCodeUseCase(attachmentRemoteDataSource)
    }

    @JvmStatic
    fun provideGetAttachmentsUseCase(): GetAttachments {
        return GetAttachments(attachmentRemoteDataSource)
    }

}