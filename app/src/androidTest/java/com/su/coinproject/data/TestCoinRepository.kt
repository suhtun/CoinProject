package com.su.coinproject.data

import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.mock.mockCoinDetailValue
import com.su.coinproject.mock.mockCoinValue
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCoinRepository {

    private lateinit var successRepository: CoinRepository
    private lateinit var failureRepository: CoinRepository

    @Before
    fun setUp() {
        successRepository = SuccessReturnRepositoryImplTest()
        failureRepository = FailureReturnRepositoryImplTest()
    }

    @Test
    fun loadCoinsSuccessState() = runTest {
        val result = successRepository.getCoins(1, 1)
        val expectedResult = Result.Success(listOf(mockCoinValue))
        assertEquals(expectedResult, result)
    }

    @Test
    fun loadCoinDetailSuccessState() = runTest {
        val result = successRepository.getCoinDetail("67YlI0K1b")
        val expectedResult = Result.Success(mockCoinDetailValue)
        assertEquals(expectedResult, result)
    }

    @Test
    fun onSearchSuccessState() = runTest {
        val result = successRepository.searchCoins("bit")
        val expectedResult = Result.Success(listOf(mockCoinValue))
        assertEquals(expectedResult, result)
    }

    @Test
    fun loadCoinsFailureState() = runTest {
        val failureResult = failureRepository.getCoins(0, 0)
        val expectedError = Result.Error(NetworkError.SERVER_ERROR)
        assertEquals(expectedError, failureResult)
    }

    @Test
    fun detailCoinFailureState() = runTest {
        val failureResult = failureRepository.getCoinDetail("12345")
        val expectedError = Result.Error(NetworkError.COIN_NOT_FOUND)
        assertEquals(expectedError, failureResult)
    }

    @Test
    fun searchCoinsFailureState() = runTest {
        val failureResult = failureRepository.searchCoins("test")
        val expectedError = Result.Error(NetworkError.EMPTY_DATA)
        assertEquals(expectedError, failureResult)
    }
}