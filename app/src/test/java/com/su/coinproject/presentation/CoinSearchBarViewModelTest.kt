package com.su.coinproject.presentation

import com.su.coinproject.data.TestRepositoryImpl
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.presentation.coin_list.CoinListViewModel
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarAction
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarViewModel
import com.su.coinproject.mock.searchCoinUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CoinSearchBarViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: CoinSearchBarViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CoinSearchBarViewModel(TestRepositoryImpl())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Clean up after each test
    }

    @Test
    fun `search coins by keyword sets Success state on successful`() = runBlocking(testDispatcher) {
//        viewModel.onAction(CoinSearchBarAction.OnSearch("BIT", false))

        val firstCoin = viewModel.state.value.coins?.first()


        assertEquals(firstCoin?.id, searchCoinUi.id)
        assertEquals(firstCoin?.name, searchCoinUi.name)
        assertEquals(firstCoin?.price, searchCoinUi.price)
        assertEquals(firstCoin?.marketCap, searchCoinUi.marketCap)
    }
}

