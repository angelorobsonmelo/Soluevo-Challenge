package com.angelomelo.cm_customer_android.application.helper.handlerstatuscode

import com.angelomelo.soluevochallenge.R


enum class HandlerErrorStatusCode(val value: Int) : MessageStatusCodeError {

    UNAUTHORIZED(401) {
        override fun getMessageFromResourceString(): Int {
           return R.string.unauthorized
        }
    },
    INTERNAL_SERVER_ERROR(500) {
        override fun getMessageFromResourceString(): Int {
            return R.string.internal_error
        }
    },
    NO_INTERNET_CONNECTION_ERROR(600) {
        override fun getMessageFromResourceString(): Int {
            return R.string.no_internet_connection_error
        }
    },
    UNKNOWN_ERROR(520) {
        override fun getMessageFromResourceString(): Int {
           return R.string.unknown_error
        }
    },

    BAD_REQUEST(400) {
        override fun getMessageFromResourceString(): Int {
            return R.string.unauthorized
        }
    };

    companion object {

        fun fromInt(statusCode: Int): HandlerErrorStatusCode? {
            return values().singleOrNull { it.value == statusCode } ?: UNKNOWN_ERROR
        }

    }

}

