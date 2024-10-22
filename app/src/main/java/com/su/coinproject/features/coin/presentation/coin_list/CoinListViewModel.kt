package com.su.coinproject.features.coin.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import kotlinx.coroutines.flow.map

class CoinListViewModel(private val pager: Pager<Int, Coin>) : ViewModel() {

    val coinListPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCoinUi() }
        }.cachedIn(viewModelScope)
}