package com.su.coinproject.features.coin.presentation.coin_list

sealed interface CoinListEvent {
    data class CoinDetailBottomUp(val isShow: Boolean): CoinListEvent
}