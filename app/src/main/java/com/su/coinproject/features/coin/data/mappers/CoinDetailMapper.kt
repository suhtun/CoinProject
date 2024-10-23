package com.su.coinproject.features.coin.data.mappers

import com.su.coinproject.features.coin.data.remote.dto.coin_detail.CoinDetailDto
import com.su.coinproject.features.coin.domain.CoinDetail

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(description, websiteUrl)
}