package com.khiemle.nab

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.khiemle.domain.openweather.entities.DataResultError
import com.khiemle.domain.openweather.entities.DataResultSuccess
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
internal class MainViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainViewModel
    private val openWeatherUseCases: IOpenWeatherUseCases = mock()
    private val observer: Observer<MainState> = mock()

    @Before
    internal fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
        viewModel = MainViewModel(
            openWeatherUseCases = openWeatherUseCases
        )
        viewModel.mainState.observeForever(observer)
    }

    @After
    internal fun tearDown() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    internal fun `observer should call be called with list of forecast when pass valid city`() {
        testDispatcher.runBlockingTest {
            val forecastResponse = getValidForeCast()
            When calling openWeatherUseCases.getDailyForecast(VALID_SEARCH_TEXT) itReturns DataResultSuccess(
                forecastResponse
            )
            viewModel.search(VALID_SEARCH_TEXT)
            verify(observer).onChanged(MainState.ShowLoading)
            advanceUntilIdle()
            verify(observer).onChanged(any(MainState.Forecasts::class.java))
        }
    }

    @Test
    internal fun `observer should call be called with error when pass invalid city`() {
        testDispatcher.runBlockingTest {
            When calling openWeatherUseCases.getDailyForecast(INVALID_SEARCH_TEXT) itReturns DataResultError(
                message = DataResultError.CITY_NOT_FOUND_MESSAGE
            )
            viewModel.search(INVALID_SEARCH_TEXT)
            verify(observer).onChanged(MainState.ShowLoading)
            advanceUntilIdle()
            verify(observer).onChanged(any(MainState.ShowErrorMessage::class.java))
        }
    }

    companion object {
        const val VALID_SEARCH_TEXT = "saigon"
        const val INVALID_SEARCH_TEXT = "sa"
    }
}
