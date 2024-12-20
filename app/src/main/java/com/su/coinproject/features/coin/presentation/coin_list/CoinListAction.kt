package com.su.coinproject.features.coin.presentation.coin_list

import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

sealed interface CoinListAction {
    data class NavigateToDetail(val coinUi: CoinUi) : CoinListAction
}