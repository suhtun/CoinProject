package com.su.coinproject.features.coin.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.su.coinproject.core.data.remote.createApiUrl
import com.su.coinproject.features.coin.data.mappers.toCoin
import com.su.coinproject.features.coin.data.remote.dto.coin_list.CoinListResponseDto
import com.su.coinproject.features.coin.domain.Coin
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoinListPagingSource(private val httpClient: HttpClient): PagingSource<Int, Coin>() {

    private val coinLimitCountPerRequest = 20

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val page = params.key ?: 1
        return try {
            val response = httpClient.get(urlString = createApiUrl("/coins?limit=${coinLimitCountPerRequest}&offset=${page * coinLimitCountPerRequest}"))
            val coins = response.body<CoinListResponseDto>().data.coins.map { it.toCoin() }
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