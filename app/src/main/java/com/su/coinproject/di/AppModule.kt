package com.su.coinproject.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.su.coinproject.core.data.remote.HttpClientFactory
import com.su.coinproject.features.coin.data.remote.CoinListPagingSource
import com.su.coinproject.features.coin.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }

    factory { CoinListPagingSource(get()) }

    single {
        Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { get<CoinListPagingSource>() }
        )
    }

    viewModel {
        CoinListViewModel(get())
    }
}