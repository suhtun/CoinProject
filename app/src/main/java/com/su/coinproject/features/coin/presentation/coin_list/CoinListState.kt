package com.su.coinproject.features.coin.presentation.coin_list

import androidx.paging.PagingData
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import kotlinx.coroutines.flow.Flow

data class CoinListState(
    val isLoading: Boolean = false,
    val selectedCoin: CoinUi? = null,
    val coins: Flow<PagingData<CoinUi>>? = null,
)

