package com.su.coinproject.features.coin.presentation.coin_search

import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi

data class CoinSearchBarState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi>? = emptyList(),
    val selectedCoin: CoinUi? = null,
    val showCoinDetail: Boolean = false,
    val isError: Pair<Boolean, NetworkError>? = Pair(false, NetworkError.UNKNOWN)
)
