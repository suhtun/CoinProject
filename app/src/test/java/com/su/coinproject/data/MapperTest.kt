package com.su.coinproject.data

import com.su.coinproject.features.coin.data.mappers.toCoin
import com.su.coinproject.features.coin.data.mappers.toCoinDetail
import com.su.coinproject.mock.mockCoinDetailDtoValue
import com.su.coinproject.mock.mockCoinDetailValue
import com.su.coinproject.mock.testCoinValue
import com.su.coinproject.mock.testcoinDtoValue
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `coinDto to Coin`() {

        val coin = testcoinDtoValue.toCoin()
        assertEquals(testCoinValue, coin)
    }

    @Test
    fun `coinDetailDto to CoinDetail`() {

        val coin = mockCoinDetailDtoValue.toCoinDetail()
        assertEquals(mockCoinDetailValue, coin)
    }
}