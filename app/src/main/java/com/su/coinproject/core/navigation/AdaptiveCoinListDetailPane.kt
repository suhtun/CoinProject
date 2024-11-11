package com.su.coinproject.core.navigation

import android.os.Parcelable
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.su.coinproject.features.coin.presentation.coin_detail.CoinDetailScreen
import com.su.coinproject.features.coin.presentation.coin_list.CoinListAction
import com.su.coinproject.features.coin.presentation.coin_list.CoinListScreen
import com.su.coinproject.features.coin.presentation.coin_list.CoinListViewModel
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<CoinItem>()

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = uiState,
                    onAction = {
                        when (it) {
                            is CoinListAction.NavigateToDetail -> {
                                navigator.navigateTo(
                                    ListDetailPaneScaffoldRole.Detail,
                                    CoinItem(coinUi = it.coinUi)
                                )
                            }
                        }
                    },
                )
            }
        },
        detailPane = {
            AnimatedPane {
                val coinUi = navigator.currentDestination?.content?.coinUi
                CoinDetailScreen(
                    modifier = modifier,
                    coin = coinUi,
                    onBack = { navigator.navigateBack() },
                )

            }
        }
    )

}

@Parcelize
class CoinItem(val coinUi: CoinUi) : Parcelable