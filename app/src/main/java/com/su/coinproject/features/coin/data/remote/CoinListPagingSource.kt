package com.su.coinproject.features.coin.data.remote

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.presentation.util.toString
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.core.domain.util.Result

class CoinListPagingSource(
    private val repository: CoinRepository
) : PagingSource<Int, Coin>() {

    private val limitCoins = 20

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val page = params.key ?: 1

        if (page < 1) return LoadResult.Page(emptyList(), prevKey = null, nextKey = page + 1)

        return try {
            // Call the repository to get coins with pagination
            when (val result = repository.getCoins(limitCoins, page * limitCoins)) {
                is Result.Error -> {
                    LoadResult.Error(CustomPagingException(result.error)) // Return error load result
                }
                is Result.Success -> {
                    val loadedCoins = result.data // Get the list of coins
                    LoadResult.Page(
                        data = loadedCoins, // The list of coins retrieved
                        prevKey = page - 1, // Previous key logic
                        nextKey = if (loadedCoins.isEmpty()) null else page + 1 // Next key logic
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

class CustomPagingException(val networkError: NetworkError) : Exception()
