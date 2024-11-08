package com.su.coinproject.features.coin.presentation.coin_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.su.coinproject.core.presentation.components.AppAsyncImage
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.previewCoinUi

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
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

//    Card(
//        modifier = Modifier
//            .clickable(onClick = { onClick(coinUi) })
//            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
//        shape = RoundedCornerShape(12.dp),
//        border = BorderStroke(
//            width = 1.dp,
//            color = MaterialTheme.colorScheme.primary
//        ),
//        elevation = CardDefaults.cardElevation(6.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceContainer,
//            contentColor = contentColor
//        ),
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick(coinUi) }) {
        Row(
            modifier = modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = coinUi.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryFontColor
                )
                Text(
                    text = coinUi.symbol,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = secondaryFontColor
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$${coinUi.price.formatted}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryFontColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                PriceChange(
                    change = coinUi.change
                )
            }
        }
    }
}

@Preview
@Composable
private fun CoinListItemPreview() {
    CoinListItem(
        coinUi = previewCoinUi,
        onClick = { },
        modifier = Modifier.background(
            MaterialTheme.colorScheme.background
        )
    )
}
