package com.su.coinproject.features.coin.presentation.coin_list

import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

data class CoinListState(
    val selectedCoin: CoinUi? = null,
    val isLoading: Boolean = false,
    val isShowingCoinDetailBottomUp: Boolean = false,
    val isShowingCoinDetailSharedElements: Boolean = false,
    val isErrorDisplayed:Boolean = false
)

