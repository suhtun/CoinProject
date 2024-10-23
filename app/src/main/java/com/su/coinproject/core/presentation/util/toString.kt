package com.su.coinproject.core.presentation.util

import android.content.Context
import com.su.coinproject.R
import com.su.coinproject.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
        NetworkError.JSON_CONVERT -> R.string.error_serialization
        NetworkError.COIN_NOT_FOUND -> R.string.error_coin_not_found
        NetworkError.VALIDATION_ERROR -> R.string.error_validation
    }
    return context.getString(resId)
}