package com.su.coinproject.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.su.coinproject.core.data.remote.HttpClientFactory
import com.su.coinproject.features.coin.data.CoinRepositoryImpl
import com.su.coinproject.features.coin.data.remote.CoinListPagingSource
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarViewModel
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.features.coin.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }

    factoryOf(::CoinListPagingSource)

    single {
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 1, initialLoadSize = 20),
            pagingSourceFactory = { get<CoinListPagingSource>() }
        )
    }

    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()

    viewModelOf(::CoinListViewModel)
    viewModelOf(::CoinSearchBarViewModel)
}