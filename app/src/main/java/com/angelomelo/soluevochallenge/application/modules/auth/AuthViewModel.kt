package com.angelomelo.soluevochallenge.application.modules.auth

import com.angelomelo.soluevochallenge.application.injections.InjectionUseCase
import com.angelomelo.soluevochallenge.application.usecases.UseCase
import com.angelomelo.soluevochallenge.application.utils.BaseViewModel
import com.angelomelo.soluevochallenge.domain.User

class AuthViewModel : BaseViewModel<Void>() {

    private val mAuthUseCase = InjectionUseCase.provideAuthseCase()

    fun auth(user: User) {
        mAuthUseCase.auth(user, object : UseCase.VoidUseCaseCallback {
            override fun onSuccess() {
                successObserver.value = null
            }

            override fun onEmptyData() {

            }

            override fun isLoading(isLoading: Boolean) {
                isLoadingObserver.value = isLoading
            }

            override fun onError(errorDescription: String) {
                errorObserver.value = errorDescription
            }

        })
    }

}
