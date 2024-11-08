package com.su.coinproject.features.coin.presentation.coin_list

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun TopRankCoinListView(
    coins: List<CoinUi?>,
    showBottomUp: (CoinUi) -> Unit = {},
    showSharedElementTransition: (CoinUi) -> Unit = {}
) {
    if (coins.isEmpty()) return

    Row(
        modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        coins[0]?.let {
            TopRankCoinItem(
                coinUi = it,
                onClick = { coinUi -> showSharedElementTransition(coinUi) })
        }
        coins[1]?.let {
            TopRankCoinItem(
                coinUi = it,
                onClick = { coinUi -> showBottomUp(coinUi) })
        }
        coins[2]?.let {
            TopRankCoinItem(
                coinUi = it,
                onClick = { coinUi -> showSharedElementTransition(coinUi) })
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