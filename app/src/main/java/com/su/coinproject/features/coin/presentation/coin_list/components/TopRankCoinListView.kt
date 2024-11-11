package com.su.coinproject.features.coin.presentation.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.previewCoinUi
import com.su.coinproject.ui.theme.CoinProjectTheme

@Composable
fun TopRankCoinListView(
    coins: List<CoinUi?>,
    navigateToDetail: (CoinUi) -> Unit = {},
) {
    if (coins.isEmpty()) return

    Row(
        modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        repeat(3) {
            coins[it]?.let { coin ->
                TopRankCoinItem(
                    coinUi = coin,
                    onClick = { coinUi -> navigateToDetail(coinUi) })
            }
        }
    }
}

@Preview
@Composable
fun TopRankCoinListViewPreview(modifier: Modifier = Modifier) {
    CoinProjectTheme {
        TopRankCoinListView(List(3) { previewCoinUi })
    }
}