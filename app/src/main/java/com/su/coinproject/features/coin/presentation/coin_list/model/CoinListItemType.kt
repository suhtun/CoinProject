package com.su.coinproject.features.coin.presentation.coin_list.model

sealed class CoinListItemType {
    data class CoinUiType(val coinUi: CoinUi): CoinListItemType()
    data object InviteFriendType: CoinListItemType()
}