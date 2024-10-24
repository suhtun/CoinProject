package com.su.coinproject.features.coin.presentation.coin_search

import com.su.coinproject.features.coin.presentation.coin_list.CoinListAction
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

sealed interface CoinSearchBarAction {
    data class OnSearch(val keyword:String, val showLoading:Boolean): CoinSearchBarAction
    data class OnCoinClick(val coinUi: CoinUi): CoinSearchBarAction
}