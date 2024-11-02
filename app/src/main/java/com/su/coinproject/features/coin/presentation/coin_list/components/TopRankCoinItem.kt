package com.su.coinproject.features.coin.presentation.coin_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.su.coinproject.R
import com.su.coinproject.core.presentation.components.AppAsyncImage
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import com.su.coinproject.ui.theme.CoinProjectTheme

@Composable
fun TopRankCoinItem(
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val secondaryFontColor = if (isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
    }

    Card(
        modifier = modifier
            .widthIn(min = 105.dp, max = 180.dp)
            .clickable(onClick = { onClick(coinUi) }),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)

            Text(
                text = coinUi.symbol,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = primaryFontColor
            )
            Text(
                text = coinUi.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryFontColor,
                modifier = Modifier
                    .width(80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            PriceChange(
                change = coinUi.change
            )
        }
    }
}

@Preview
@Composable
private fun CoinListItemPreview() {
    CoinProjectTheme {

        TopRankCoinHorizontalItem(
            coinUi = previewTopCoin,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }

}

@Composable
fun TopRankCoinHorizontalItem(
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.White
    }

    Card(
        modifier = modifier
            .widthIn(min = 105.dp, max = 180.dp)
            .clickable(onClick = { onClick(coinUi) }),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)
            Row() {
                Text(
                    text = coinUi.symbol,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryFontColor
                )
                PriceChange(
                    change = coinUi.change
                )
            }
        }
    }
}

internal val previewTopCoin = Coin(
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