package com.su.coinproject.features.coin.data.mappers

import com.su.coinproject.features.coin.data.remote.dto.CoinDto
import com.su.coinproject.features.coin.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = uuid,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
        price = price,
        change = change
    )
}