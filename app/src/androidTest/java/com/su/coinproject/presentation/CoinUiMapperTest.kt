package com.su.coinproject.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import com.su.coinproject.mock.mockCoinValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoinUiMapperTest {

    @Test
    fun toCoinUiMapper() {

        val result = mockCoinValue.toCoinUi()
        assertEquals("67,482.871", result.price.formatted)
        assertEquals("1.33 trillion", result.marketCap)
        assertEquals("0.32", result.change.formatted)
    }
}