package com.angelomelo.soluevochallenge.application.usecases

interface UseCase {

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

    interface VoidUseCaseCallback {
        fun onSuccess()
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

}