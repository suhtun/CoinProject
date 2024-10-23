package com.su.coinproject.features.coin.presentation.coin_list

import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

data class CoinListState(
    val showCoinDetail: Boolean = false,
    val selectedCoin: CoinUi? = null,
    val loadingCoilDetail: Boolean = false
)

