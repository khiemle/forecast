package com.khiemle.data.repositories

import com.google.gson.Gson
import org.junit.After
import org.mockito.Mockito
import com.khiemle.data.remote.IOpenWeatherApi
import com.khiemle.data.response.OpenWeatherGetDailyResponse
import com.khiemle.data.room.ForecastDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.internal.matchers.apachecommons.ReflectionEquals
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
internal class OpenWeatherTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val api: IOpenWeatherApi = Mockito.mock(IOpenWeatherApi::class.java)
    private val database: ForecastDatabase = Mockito.mock(ForecastDatabase::class.java)
    private lateinit var openWeather: OpenWeather
    private val gson = Gson()

    @Before
    internal fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        openWeather = OpenWeather(api = api, database = database, appId = "api key")
    }

    @After
    internal fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    internal fun `should return success data when pass valid value`() {
        testDispatcher.runBlockingTest {
            When calling api.getDaily(city = "Saigon", days = 7, appId = "api key", units = "metric") itReturns getSaigonForecastDaily()
            val expectedResult = OpenWeatherResultSuccess(getSaigonForecastDaily())
            val actualResult = openWeather.getDaily(cityName = "Saigon", numberDayOfForecast = 7, units = "metric")

            Verify on api that api.getDaily(any(), any(), any(), any()) was called
            assertTrue(ReflectionEquals(expectedResult).matches(actualResult))
        }
    }

    @Test
    internal fun `should return 404 error when pass unknown city value`() {
        testDispatcher.runBlockingTest {
            val responseErrorMock = Mockito.mock(ResponseBody::class.java)
            val cityNotFoundThrowable = HttpException(Response.error<Nothing>(404, responseErrorMock))
            When calling api.getDaily(city = "unknown city", days = 7, appId = "api key", units = "metric") itThrows cityNotFoundThrowable
            val actualResult = openWeather.getDaily(cityName = "unknown city", numberDayOfForecast = 7, units = "metric")

            Verify on api that api.getDaily(any(), any(), any(), any()) was called
            assertTrue(actualResult is OpenWeatherResultError)
            assertTrue(actualResult.code == 404)
        }
    }

    private fun getSaigonForecastDaily(): OpenWeatherGetDailyResponse {
        return gson.fromJson(saigonForecastDailyData, OpenWeatherGetDailyResponse::class.java)
    }
}