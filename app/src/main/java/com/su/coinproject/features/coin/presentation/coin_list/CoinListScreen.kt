package com.su.coinproject.features.coin.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.su.coinproject.R
import com.su.coinproject.core.presentation.components.AppLoadingView
import com.su.coinproject.core.presentation.components.ErrorMessageView
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinDetail
import com.su.coinproject.features.coin.presentation.coin_detail.CoinDetailView
import com.su.coinproject.features.coin.presentation.coin_list.components.CoinListItem
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.emptyCoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val coins: LazyPagingItems<CoinUi> = viewModel.coinListPagingFlow.collectAsLazyPagingItems()

    val state by viewModel.state.collectAsStateWithLifecycle()

    var isRefreshing by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        PullToRefreshBox(isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                coins.refresh() // Refresh the LazyPagingItems
            }) {
            LazyColumn(modifier = Modifier.background(Color.White)) {
                item {
                    Text(
                        text = stringResource(id = R.string.label_coin),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(coins.itemCount) { index ->
                    coins[index]?.let { coin ->
                        CoinListItem(
                            coin,
                            onClick = { coinUi ->
                                viewModel.onAction(CoinListAction.OnCoinClick(coinUi))
                            })
                    }
                }

                item {
                    when {
                        coins.loadState.refresh is LoadState.Loading -> {
                            AppLoadingView()
                        }

                        coins.loadState.refresh is LoadState.Error -> {
                            val e = coins.loadState.refresh as LoadState.Error
                            ErrorMessageView(message = e.error.message) {
                                coins.retry()
                            }
                        }

                        coins.loadState.append is LoadState.Error -> {
                            val e = coins.loadState.append as LoadState.Error
                            ErrorMessageView(message = e.error.message) {
                                coins.retry()
                            }
                        }

                        coins.loadState.append is LoadState.Loading -> {
                            AppLoadingView()
                        }
                    }
                }
            }
        }


        if (state.loadingCoilDetail) {
            AppLoadingView()
        }

        if (state.showCoinDetail) {
            CoinDetailView()
        }

        LaunchedEffect(coins.loadState.refresh) {
            if (coins.loadState.refresh !is LoadState.Loading) {
                isRefreshing = false
            }
        }

    }
}

internal val previewCoin = Coin(
    id = "Qwsogvtv82FCd",
    name = "Bitcoin",
    color = "#f7931A",
    symbol = "BTC",
    price = 1241273958896.75,
    change = 0.1,
    marketCap = 1241273958896.54,
    iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg",
).toCoinUi()


