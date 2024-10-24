package com.su.coinproject.core.data.remote

import com.su.coinproject.BuildConfig

fun createApiUrl(url: String): String {
//    val baseUrl = BuildConfig.BASE_URL
    val baseUrl = "https://api.coinranking.com/v2/"
    return when {
        url.contains(baseUrl) -> baseUrl
        url.startsWith("/") -> baseUrl + url.drop(1)
        else -> baseUrl
    }
}