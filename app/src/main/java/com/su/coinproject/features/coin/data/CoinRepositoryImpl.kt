package com.su.coinproject.features.coin.data

import com.su.coinproject.core.data.remote.createApiUrl
import com.su.coinproject.core.data.remote.safeCall
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import com.su.coinproject.core.domain.util.map
import com.su.coinproject.features.coin.data.mappers.toCoinDetail
import com.su.coinproject.features.coin.data.remote.dto.coin_detail.CoinDetailResponseDto
import com.su.coinproject.features.coin.domain.CoinDetail
import com.su.coinproject.features.coin.domain.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinRepositoryImpl(private val httpClient: HttpClient) : CoinRepository {

    override suspend fun getCoinDetail(uuid: String): Result<CoinDetail, NetworkError> {
        return safeCall<CoinDetailResponseDto> {
            httpClient.get(urlString = createApiUrl("/coin/$uuid"))
        }.map { response ->
            response.data.coin.toCoinDetail()
        }
    }
}