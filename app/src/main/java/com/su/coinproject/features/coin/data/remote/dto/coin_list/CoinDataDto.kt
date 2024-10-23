package com.su.coinproject.features.coin.data.remote.dto.coin_list

import kotlinx.serialization.Serializable

@Serializable
data class CoinDataDto(val coins: List<CoinDto>)
