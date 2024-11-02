package com.su.coinproject.features.coin.presentation.coin_list.model

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Parcelable
import com.su.coinproject.features.coin.domain.Coin
import kotlinx.parcelize.Parcelize
import java.util.Locale
import kotlin.math.abs

@Parcelize
data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val color: String?,
    val price: DisplayableNumber,
    val marketCap: String,
    val change: DisplayableNumber,
    val rank: Int,
    val sparkline: List<Double>,
    var description: String? = null,
    var websiteUrl: String? = null
): Parcelable

internal val previewCoin = Coin(
    id = "bitcoin",
    name = "Bitcoin",
    color = "#f7931A",
    symbol = "BTC",
    price = 1241273958896.75,
    change = 0.1,
    rank = 1,
    sparkline = listOf(1.0, 2.0, 3.0, 4.0, 5.0),
    marketCap = 1241273958896.54,
    iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"
)

internal val previewCoinUi = Coin(
    id = "bitcoin",
    name = "Bitcoin",
    color = "#f7931A",
    symbol = "BTC",
    price = 1241273958896.75,
    change = 0.1,
    rank = 1,
    sparkline = listOf(1.0, 2.0, 3.0, 4.0, 5.0),
    marketCap = 1241273958896.54,
    iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"
).toCoinUi()

@Parcelize
data class DisplayableNumber(
    val value: Double,
    val formatted: String
): Parcelable

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
        rank = rank,
        sparkline = sparkline
    )

}