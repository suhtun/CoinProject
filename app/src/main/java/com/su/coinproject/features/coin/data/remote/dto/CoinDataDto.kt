package com.su.coinproject.features.coin.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDataDto(val coins: List<CoinDto>)
