package com.su.coinproject.features.coin.presentation.coin_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.su.coinproject.features.coin.presentation.coin_detail.CoinDetailScreen
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListDetailSharedElements(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var showDetail by remember {
        mutableStateOf(false)
    }

    var selectedCoin: CoinUi? by remember {
        mutableStateOf(null)
    }

    AnimatedContent(showDetail, label = "basic") { targetScope ->
        if (!targetScope) {
            CoinListScreen(
                modifier = modifier,
                onShowDetail = { showDetail = !showDetail },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        } else {
            CoinDetailScreen(
                modifier = modifier,
                coin = selectedCoin,
                onBack = { showDetail = !showDetail },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }

}