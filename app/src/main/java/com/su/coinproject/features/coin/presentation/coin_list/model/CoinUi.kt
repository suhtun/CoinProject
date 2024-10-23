package com.su.coinproject.features.coin.presentation.coin_list.model

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import com.su.coinproject.features.coin.domain.Coin
import java.util.Locale
import kotlin.math.abs

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val color:String?,
    val price: DisplayableNumber,
    val marketCap: String,
    val change: DisplayableNumber,
    var description: String? = null,
    var websiteUrl: String? = null
)

val emptyCoinUi =
    CoinUi("", "", "", "","", 0.0.toDisplayableNumber(), "0.0", 0.0.toDisplayableNumber(), "", "")

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Double.toDisplayableNumberwithSuffix(): String {
    val absValue = abs(this)
    val df = DecimalFormat("#.##")

    return when {
        absValue >= 1_000_000_000_000 -> "${df.format(this / 1_000_000_000_000)} trillion"
        absValue >= 1_000_000_000 -> "${df.format(this / 1_000_000_000)} billion"
        absValue >= 1_000_000 -> "${df.format(this / 1_000_000)} million"
        else -> df.format(this)
    }
}

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

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
        color = color,
        price = price.toDisplayableNumber(),
        marketCap = marketCap.toDisplayableNumberwithSuffix(),
        change = change.toDisplayableNumber(),
    )

}