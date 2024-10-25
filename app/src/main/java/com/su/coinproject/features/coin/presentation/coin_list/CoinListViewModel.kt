package com.su.coinproject.features.coin.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.su.coinproject.core.domain.util.onError
import com.su.coinproject.core.domain.util.onSuccess
import com.su.coinproject.features.coin.domain.CoinData
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinListItemType
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    pager: Pager<Int, CoinData>,
    private val coinRepository: CoinRepository,
) : ViewModel() {

    var coinListPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { item ->
                if (item is CoinData.CoinCard) {
                    CoinListItemType.CoinUiType(item.coin.toCoinUi())
                } else
                    CoinListItemType.InviteFriendType

            }
        }
        .cachedIn(viewModelScope)


    private val _state = MutableStateFlow(CoinListState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CoinListState()
    )


//todo: need workaround for logic
//    init {
//        // Launch a coroutine to refresh data every 10 seconds
//        viewModelScope.launch {
//            while (true) {
//                delay(10_000) // Wait for 10 seconds
//                _state.update {
//                    it.copy(
//                        refreshPaing = true
//                    )
//                }
//            }
//        }
//    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                loadCoinDetail(action.coinUi)
            }

            is CoinListAction.OnDismissCoinDetailBottomUp -> {
                _state.update {
                    it.copy(
                        showCoinDetail = false
                    )
                }
            }
        }
    }

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
                            showCoinDetail = true,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            showCoinDetail = false,
                            isLoading = false
                        )
                    }
                }
        }
    }
}
