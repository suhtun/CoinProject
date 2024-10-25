package com.su.coinproject.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.su.coinproject.features.coin.presentation.coin_list.model.toDisplayableNumber
import com.su.coinproject.features.coin.presentation.coin_list.model.toDisplayableNumberwithSuffix
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.text.NumberFormat

@RunWith(AndroidJUnit4::class)
class DisplayNumberableTest {

    @Test
    fun testDisplayableNumber() {
        val formattedNumber = 1234.123456.toDisplayableNumber()
        assertEquals("1,234.12346", formattedNumber.formatted)
    }

    @Test
    fun testDisplayableNumberWithSuffix() {
        val trillionNumberDisplay = 1_000_000_000_000.00.toDisplayableNumberwithSuffix()
        assertEquals("1 trillion", trillionNumberDisplay)

        val billionNumberDisplay = 1_000_000_000.00.toDisplayableNumberwithSuffix()
        assertEquals("1 billion", billionNumberDisplay)

        val millionNumberDisplay = 1_000_000.00.toDisplayableNumberwithSuffix()
        assertEquals("1 million", millionNumberDisplay)

        val numberDisplay = 1_000_00.00.toDisplayableNumberwithSuffix()
        assertEquals("100000", numberDisplay)
    }
}