package com.su.coinproject.features.coin.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.su.coinproject.core.data.data.AppDatabase
import com.su.coinproject.core.data.remote.createApiUrl
import com.su.coinproject.features.coin.data.local.CoinEntity
import com.su.coinproject.features.coin.data.mappers.toCoinEntity
import com.su.coinproject.features.coin.data.remote.dto.coin_list.CoinListResponseDto
import com.su.coinproject.features.coin.domain.CoinData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CoinsRemoteMediator(
    private val httpClient: HttpClient,
    private val db: AppDatabase
) : RemoteMediator<Int, CoinEntity>() {

    private val limitCoins = 10

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        state.config.pageSize + 1
                    }
                }
            }

            val coins =
                httpClient.get(urlString = createApiUrl("/coins?limit=${limitCoins}&offset=${(loadKey * limitCoins)}"))
                    .body<CoinListResponseDto>().data.coins

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.clearAll()
                }
                val coinEnteritis = coins.map { it.toCoinEntity() }
                db.dao.upsertAll(coinEnteritis)
            }

            MediatorResult.Success(
                endOfPaginationReached = coins.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}