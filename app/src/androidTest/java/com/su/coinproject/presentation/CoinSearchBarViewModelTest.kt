package com.su.coinproject.presentation

import app.cash.turbine.test
import com.su.coinproject.data.SuccessReturnRepositoryImplTest
import com.su.coinproject.features.coin.domain.CoinRepository
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarAction
import com.su.coinproject.features.coin.presentation.coin_search.CoinSearchBarViewModel
import com.su.coinproject.mock.mockCoinValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CoinSearchBarViewModelTest {

    // Mock repository
    private val repository: CoinRepository = SuccessReturnRepositoryImplTest()

    // Use a TestDispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    // The ViewModel under test
    private lateinit var viewModel: CoinSearchBarViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CoinSearchBarViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onSearchSuccessState() = runTest {

        viewModel.state.test {
            assertEquals(awaitItem().isLoading, false)
            viewModel.onAction(CoinSearchBarAction.OnSearch("bit",true))
            assertEquals(awaitItem().coins?.get(0), mockCoinValue)
        }
    }
}






