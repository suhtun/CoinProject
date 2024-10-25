package com.su.coinproject.data

import com.google.gson.Gson
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import com.su.coinproject.features.coin.data.mappers.toCoin
import com.su.coinproject.features.coin.data.mappers.toCoinDetail
import com.su.coinproject.features.coin.data.remote.dto.coin_detail.CoinDetailResponseDto
import com.su.coinproject.features.coin.data.remote.dto.coin_list.CoinListResponseDto
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinDetail
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.mock.mockCoinDetailResponse
import com.su.coinproject.mock.mockCoinListResponse
import com.su.coinproject.mock.mockSerchCoinsResponse
import com.su.coinproject.mock.searchCoin
import com.su.coinproject.mock.searchCoinUi
import com.su.coinproject.util.fromJson
import kotlinx.coroutines.flow.asFlow

class TestRepositoryImpl : CoinRepository {

    val coinsResponse: CoinListResponseDto = fromJson(mockCoinListResponse)
    val coinDetailResponse: CoinDetailResponseDto = fromJson(mockCoinDetailResponse)
    val searchCoinsResponse: CoinListResponseDto = fromJson(mockSerchCoinsResponse)

    override suspend fun getCoins(limit: Int, offset: Int): Result<List<Coin>, NetworkError> {
        return Result.Success(coinsResponse.data.coins.map { it.toCoin() })
    }

    override suspend fun getCoinDetail(uuid: String): Result<CoinDetail, NetworkError> {
        return Result.Success(coinDetailResponse.data.coin.toCoinDetail())
    }

    override suspend fun searchCoins(keywords: String): Result<List<Coin>, NetworkError> {
//        return Result.Success(searchCoinsResponse.data.coins.map { it.toCoin() })
        return Result.Success(listOf(searchCoin))
    }

}