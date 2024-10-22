package com.su.coinproject.core.data.remote

import com.su.coinproject.BuildConfig

fun createApiUrl(url: String): String {
    val baseUrl = BuildConfig.BASE_URL
    return when {
        url.contains(baseUrl) -> baseUrl
        url.startsWith("/") -> baseUrl + url.drop(1)
        else -> baseUrl
    }
}