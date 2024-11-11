package com.su.coinproject.features.coin.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.su.coinproject.core.domain.util.onError
import com.su.coinproject.core.domain.util.onSuccess
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val pager: Pager<Int, Coin>,
    private val coinRepository: CoinRepository,
) : ViewModel() {

    private var coinListPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { item ->
                item.toCoinUi()

            }
        }
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            _state.update {
                it.copy(coins = coinListPagingFlow)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )


    private fun loadCoinDetail(coinUi: CoinUi) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCoin = coinUi,
                    isLoading = true,
                )
            }

            coinRepository
                .getCoinDetail(coinUi.id)
                .onSuccess { coinDetail ->
                    _state.update {
                        it.copy(
                            selectedCoin = coinUi.copy(
                                description = coinDetail.description,
                                websiteUrl = coinDetail.websiteUrl
                            ),
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
        }
    }
}
