package com.khiemle.nab

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.khiemle.domain.models.DataResultError
import com.khiemle.domain.models.DataResultSuccess
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.nab.presentation.MainState
import com.khiemle.nab.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
internal class MainViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainViewModel
    private val openWeatherUseCases: IOpenWeatherUseCases = mock()
    private val observer: Observer<MainState> = mock()

    @Captor
    var captor: ArgumentCaptor<MainState.Forecasts>? = null

    @Before
    internal fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
        captor = ArgumentCaptor.forClass(MainState.Forecasts::class.java)
        viewModel = MainViewModel(
            openWeatherUseCases = openWeatherUseCases,
            dispatcher = testDispatcher
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
            When calling openWeatherUseCases.getDailyForecastV2(
                VALID_SEARCH_TEXT,
                timestamp = 100L
            ) itReturns flow {
                emit(
                    DataResultSuccess(
                        forecastResponse
                    )
                )
            }
            viewModel.searchV2(VALID_SEARCH_TEXT, timestamp = 100L)
            advanceUntilIdle()
            verify(observer).onChanged(MainState.ShowLoading)
            advanceUntilIdle()
            verify(observer).onChanged(any(MainState.Forecasts::class.java))
        }
    }

    @Test
    internal fun `observer should call be called with empty list of forecast when clear text`() {
        testDispatcher.runBlockingTest {
            viewModel.clearSearchText()
            verify(observer).onChanged(captor?.capture())
            assertEquals(true, captor?.value?.items?.isEmpty())
        }
    }

    @Test
    internal fun `observer should call be called with error when pass invalid city`() {
        testDispatcher.runBlockingTest {
            When calling openWeatherUseCases.getDailyForecastV2(INVALID_SEARCH_TEXT, timestamp = 100L) itReturns flow {
                emit(DataResultError(
                    message = DataResultError.CITY_NOT_FOUND_MESSAGE
                ))
            }
            viewModel.searchV2(INVALID_SEARCH_TEXT, timestamp = 100L)
            advanceUntilIdle()
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
