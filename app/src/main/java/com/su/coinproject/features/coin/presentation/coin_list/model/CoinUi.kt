package com.su.coinproject.features.coin.presentation.coin_list.model

import android.icu.text.NumberFormat
import com.su.coinproject.features.coin.domain.Coin
import java.util.Locale

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: DisplayableNumber,
    val change: DisplayableNumber
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 5
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun Coin.toCoinUi(): CoinUi{
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
        price = price.toDisplayableNumber(),
        change = change.toDisplayableNumber()
    )

}