package com.su.coinproject.data

import com.su.coinproject.core.data.util.fromJson
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

class SuccessReturnRepositoryImplTest : CoinRepository {

    private val coinsResponse: CoinListResponseDto = fromJson(mockCoinListResponse)
    private val coinDetailResponse: CoinDetailResponseDto = fromJson(mockCoinDetailResponse)
    private val searchCoinsResponse: CoinListResponseDto = fromJson(mockCoinListResponse)

    override suspend fun getCoins(limit: Int, offset: Int): Result<List<Coin>, NetworkError> {
        return Result.Success(coinsResponse.data.coins.map { it.toCoin() })
    }

    override suspend fun getCoinDetail(uuid: String): Result<CoinDetail, NetworkError> {
        return Result.Success(coinDetailResponse.data.coin.toCoinDetail())
    }

    override suspend fun searchCoins(keywords: String): Result<List<Coin>, NetworkError> {
        return Result.Success(searchCoinsResponse.data.coins.map { it.toCoin() })
    }

}