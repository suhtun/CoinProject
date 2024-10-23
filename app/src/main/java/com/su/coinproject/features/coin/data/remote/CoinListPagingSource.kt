package com.su.coinproject.features.coin.data.remote

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.su.coinproject.core.data.remote.createApiUrl
import com.su.coinproject.core.data.remote.safeCall
import com.su.coinproject.core.domain.util.map
import com.su.coinproject.core.domain.util.onError
import com.su.coinproject.core.domain.util.onSuccess
import com.su.coinproject.core.presentation.util.toString
import com.su.coinproject.features.coin.data.mappers.toCoin
import com.su.coinproject.features.coin.data.remote.dto.coin_list.CoinListResponseDto
import com.su.coinproject.features.coin.domain.Coin
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinListPagingSource(
    private val context: Context,
    private val httpClient: HttpClient
) : PagingSource<Int, Coin>() {

    private val limitCoins = 10

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val page = params.key ?: 1
        return try {
            var coins: List<Coin> = emptyList()
            safeCall<CoinListResponseDto> {
                httpClient.get(urlString = createApiUrl("/coins?limit=${limitCoins}&offset=${page * limitCoins}"))
            }.map { response ->
                response.data.coins.map { it.toCoin() }
            }.onSuccess { loadedCoins ->
                coins = loadedCoins
            }.onError { error ->
                throw CustomPagingException(error.toString(context))
            }

            LoadResult.Page(
                data = coins,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (coins.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

class CustomPagingException(message: String) : Exception(message)

internal val simpleCoins = List(10) {
    Coin(
        id = "bitcoin",
        name = "Bitcoin",
        color = "#f7931A",
        symbol = "BTC",
        price = 1241273958896.75,
        change = 0.1,
        marketCap = 1241273958896.54,
        iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"
    )
}