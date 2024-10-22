package com.su.coinproject.features.coin.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val uuid: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val change: Double
)