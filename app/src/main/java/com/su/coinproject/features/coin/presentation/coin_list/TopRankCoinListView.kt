package com.su.coinproject.features.coin.presentation.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.su.coinproject.features.coin.presentation.coin_list.components.TopRankCoinItem
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.previewCoinUi
import com.su.coinproject.ui.theme.CoinProjectTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopRankCoinListView(coins: List<CoinUi>) {
    FlowRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally)
    ){
        repeat(coins.size) { index ->
            TopRankCoinItem(coinUi = coins[index])
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