package com.su.coinproject.features.coin.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.su.coinproject.R
import com.su.coinproject.core.presentation.components.AppLoadingView
import com.su.coinproject.core.presentation.components.ErrorMessageView
import com.su.coinproject.features.coin.data.remote.CustomPagingException
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarView
import com.su.coinproject.features.coin.presentation.coin_list.components.CoinListItem
import com.su.coinproject.features.coin.presentation.coin_list.components.TopRankCoinListView
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.previewCoinUi
import com.su.coinproject.ui.theme.CoinProjectTheme
import kotlinx.coroutines.flow.flowOf

const val maxTopBoxSize = 210f
const val minTopBoxSize = 0f

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
)
@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    val lazyPagingCoins: LazyPagingItems<CoinUi> =
        state.coins?.collectAsLazyPagingItems() ?: return

    var isRefreshing = (lazyPagingCoins.loadState.refresh is LoadState.Loading)

    var currentImageSize by remember {
        mutableFloatStateOf(maxTopBoxSize)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y.toInt()
                val newImageSize = currentImageSize + delta
                val perviousSize = currentImageSize
                currentImageSize = newImageSize.coerceIn(minTopBoxSize, maxTopBoxSize)
                val consumed = currentImageSize - perviousSize

                return Offset(0f, consumed)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    Column(
        Modifier
            .semantics {
                testTagsAsResourceId = true
            }
            .fillMaxSize()
    ) {

        CoinSearchBarView()

        Box(
            Modifier.nestedScroll(connection = nestedScrollConnection),
        ) {
            PullToRefreshBox(isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    lazyPagingCoins.refresh()
                    isRefreshing = false
                }) {

                val topRanks =
                    lazyPagingCoins.itemSnapshotList.sortedByDescending { it?.rank }.take(3)

                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(currentImageSize.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = stringResource(id = R.string.label_coin),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    TopRankCoinListView(coins = topRanks,
                        navigateToDetail = { coin ->
                            onAction(CoinListAction.NavigateToDetail(coin))
                        })
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = currentImageSize.dp)
                        .testTag("coin_list"),
                ) {
                    items(lazyPagingCoins.itemCount) { index ->
                        lazyPagingCoins[index]?.let { coinUi ->
                            CoinListItem(
                                coinUi = coinUi,
                                onClick = { coin ->
                                    onAction(CoinListAction.NavigateToDetail(coin))
                                })

                        }
                    }

                    item {
                        when (val loadState = lazyPagingCoins.loadState.refresh) {
                            is LoadState.Error -> {
                                val e = loadState.error as? CustomPagingException
                                ErrorMessageView(message = e?.networkError.toString()) {
                                    lazyPagingCoins.retry()
                                }
                            }
                            else ->{}
                        }

                        when (val loadState = lazyPagingCoins.loadState.append) {
                            is LoadState.Error -> {
                                val e = loadState.error as? CustomPagingException
                                ErrorMessageView(message = e?.networkError.toString()) {
                                    lazyPagingCoins.retry()
                                }
                            }

                            LoadState.Loading -> {
                                AppLoadingView()
                            }

                            is LoadState.NotLoading -> {}
                        }
                    }
                }
            }
        }
    }
}


