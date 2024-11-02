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
import com.su.coinproject.features.coin.presentation.coin_detail.CoinDetailBottomUp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BottomUpSharedElements(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var showDetail by remember {
        mutableStateOf(false)
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
            CoinDetailBottomUp(
                modifier = modifier,
            )
        }
    }

}