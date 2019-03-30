package com.angelomelo.soluevochallenge.domain.request

data class RequestObjectsForm(
    val vehicleRequest: RequestFormBase,
    val contractRequest: RequestFormBase,
    val creditorRequest: RequestFormBase
)