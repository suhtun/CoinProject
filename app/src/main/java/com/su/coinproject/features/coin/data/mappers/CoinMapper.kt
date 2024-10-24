package com.su.coinproject.features.coin.data.mappers

import com.su.coinproject.features.coin.data.local.CoinEntity
import com.su.coinproject.features.coin.data.remote.dto.coin_list.CoinDto
import com.su.coinproject.features.coin.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = uuid,
        name = name,
        color = color,
        symbol = symbol,
        iconUrl = iconUrl,
        price = price,
        marketCap = marketCap,
        change = change,
        rank=rank
    )
}

fun CoinDto.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = uuid,
        name = name,
        color = color ?: "",
        symbol = symbol,
        iconUrl = iconUrl,
        price = price,
        marketCap = marketCap,
        change = change,
        rank=rank
    )
}