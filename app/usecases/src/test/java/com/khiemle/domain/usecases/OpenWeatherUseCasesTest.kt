package com.khiemle.domain.usecases

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.data.repositories.OpenWeatherResultError
import com.khiemle.data.repositories.OpenWeatherResultSuccess
import com.khiemle.domain.openweather.entities.CityNotFoundError
import com.khiemle.domain.openweather.entities.DataResultError
import com.khiemle.domain.openweather.entities.DataResultSuccess
import com.khiemle.domain.openweather.usecases.OpenWeatherUseCases
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
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
internal class OpenWeatherUseCasesTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val repository: IOpenWeather = Mockito.mock(IOpenWeather::class.java)
    private lateinit var useCases: OpenWeatherUseCases

    @Before
    internal fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        useCases = OpenWeatherUseCases(openWeather = repository)
    }

    @After
    internal fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    internal fun `should return list of forecast when passing valid search text`() {
        testDispatcher.runBlockingTest {
            val openWeatherResponse = getValidForeCastResponse()
            When calling repository.getDaily(
                cityName = VALID_SEARCH_TEXT,
                numberDayOfForecast = OpenWeatherUseCases.FIXED_COUNT,
                units = OpenWeatherUseCases.FIXED_UNITS
            ) itReturns OpenWeatherResultSuccess(openWeatherResponse)

            val result = useCases.getDailyForecast(VALID_SEARCH_TEXT)
            assertTrue(result is DataResultSuccess)
            assertEquals(OpenWeatherUseCases.FIXED_COUNT, result.data.size)
            assertEquals(DESCRIPTION, result.data.first().description)
        }
    }

    @Test
    internal fun `should return error when passing invalid search text`() {
        testDispatcher.runBlockingTest {
            When calling repository.getDaily(
                cityName = INVALID_SEARCH_TEXT,
                numberDayOfForecast = OpenWeatherUseCases.FIXED_COUNT,
                units = OpenWeatherUseCases.FIXED_UNITS
            ) itReturns OpenWeatherResultError(
                exception = Throwable(),
                message = DataResultError.CITY_NOT_FOUND_MESSAGE,
                code = DataResultError.NOT_FOUND_CITY_CODE
            )

            val result = useCases.getDailyForecast(INVALID_SEARCH_TEXT)
            assertTrue(result is CityNotFoundError)
            assertEquals(DataResultError.NOT_FOUND_CITY_CODE, result.code)
            assertEquals(DataResultError.CITY_NOT_FOUND_MESSAGE, result.message)

        }
    }

    companion object {
        const val VALID_SEARCH_TEXT = "saigon"
        const val INVALID_SEARCH_TEXT = "sa"
    }
}