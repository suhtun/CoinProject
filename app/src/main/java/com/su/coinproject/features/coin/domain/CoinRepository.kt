package com.su.coinproject.features.coin.domain

import androidx.paging.PagingSource
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import com.su.coinproject.features.coin.data.local.CoinDao
import com.su.coinproject.features.coin.data.local.CoinEntity

interface CoinRepository {
    suspend fun getCoins(limit:Int,offset:Int): Result<List<Coin>, NetworkError>
    suspend fun getCoinDetail(uuid:String): Result<CoinDetail, NetworkError>
    suspend fun searchCoins(keywords:String): Result<List<Coin>, NetworkError>
}