package com.su.coinproject.features.coin.domain

import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result

interface CoinRepository {
    suspend fun getCoins(limit:Int,offset:Int): Result<List<Coin>, NetworkError>
    suspend fun getCoinDetail(uuid:String): Result<CoinDetail, NetworkError>
}