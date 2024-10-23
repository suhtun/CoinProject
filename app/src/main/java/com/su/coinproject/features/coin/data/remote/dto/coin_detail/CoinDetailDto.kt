package com.su.coinproject.features.coin.data.remote.dto.coin_detail

import kotlinx.serialization.Serializable


@Serializable
data class CoinDetailDto(
    val description: String,
    val websiteUrl: String
)
