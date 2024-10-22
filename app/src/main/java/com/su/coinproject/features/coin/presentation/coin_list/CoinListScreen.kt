package com.su.coinproject.features.coin.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.su.coinproject.features.coin.presentation.coin_list.components.CoinListItem
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val coins: LazyPagingItems<CoinUi> = viewModel.coinListPagingFlow.collectAsLazyPagingItems()

    LazyColumn(modifier= Modifier.background(Color.White)) {
        items(coins.itemCount) { index ->
            coins[index]?.let { coin ->
                CoinListItem(coin)
            }
        }

        when {
            coins.loadState.refresh is LoadState.Loading -> {
                // TODO: Show loading spinner for initial load
            }
            coins.loadState.refresh is LoadState.Error -> {
                val e = coins.loadState.refresh as LoadState.Error
                // TODO: Show error message for initial load
            }
            coins.loadState.append is LoadState.Error -> {
                val e = coins.loadState.append as LoadState.Error
                // TODO: Show error message for appending data
            }
            coins.loadState.append is LoadState.Loading -> {
                // TODO: Show loading spinner for appending data
            }
        }
    }
}