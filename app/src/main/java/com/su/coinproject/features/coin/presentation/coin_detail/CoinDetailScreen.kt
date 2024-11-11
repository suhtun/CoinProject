package com.su.coinproject.features.coin.presentation.coin_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.su.coinproject.core.presentation.components.AppAsyncImage
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.presentation.coin_detail.components.LiveChatView
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import com.su.coinproject.ui.theme.CoinProjectTheme
import com.su.coinproject.ui.theme.greenColor
import com.su.coinproject.ui.theme.redColor

@Composable
fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    coin: CoinUi? = null,
    onBack: () -> Unit = {},
) {
    val coinUi = coin ?: return

    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val priceColor = if (coin.change.value < 0.0) {
        redColor
    } else {
        greenColor
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .clickable { onBack() }
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onBack() }) {
                Icon(
                    imageVector =
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = primaryFontColor,
                )
            }

            AppAsyncImage(
                modifier = Modifier
                    .size(120.dp),
                url = coinUi.iconUrl,
                name = coinUi.name
            )

            val styledText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp,
                        color = primaryFontColor
                    )
                ) {
                    append("${coinUi.name} ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp, color = primaryFontColor
                    )
                ) {
                    append("(${coinUi.symbol})")
                }
            }
            Text(
                text = styledText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "$ ${coinUi.marketCap}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                color = primaryFontColor
            )

            Row {
                Text(
                    text = "${coin.change.value}%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = priceColor
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "$ ${coinUi.price.formatted}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = priceColor
                )
            }

            LiveChatView(coinUi.sparkline)

        }
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(30.dp),
            shape = RoundedCornerShape(26.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Log in to Trade",
                color = Color.Black
            )
        }
    }
}
@Preview
@Composable
fun CoinDetailScreenPreview(modifier: Modifier = Modifier) {
    CoinProjectTheme {
        CoinDetailScreen(
            coin = Coin(
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
        )
    }
}

internal val coinUi = Coin(
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