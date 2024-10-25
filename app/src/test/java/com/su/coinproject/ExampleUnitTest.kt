package com.su.coinproject

import com.su.coinproject.features.coin.presentation.coin_list.model.DisplayableNumber
import com.su.coinproject.features.coin.presentation.coin_list.model.toDisplayableNumber
import org.junit.Test

import org.junit.Assert.*
import org.koin.androidx.compose.get

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
        val testValue = 1000.00
        val expectedResult = DisplayableNumber(testValue,"1000.00")
        assertEquals(testValue.toDisplayableNumber(),expectedResult)
    }
}

fun String.getInitials() = this