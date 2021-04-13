package com.khiemle.data.repositories

import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class OpenWeatherErrorParserTest {
    @Test
    internal fun `should return OpenWeatherResultError with code 404`() {
        val responseErrorMock = Mockito.mock(ResponseBody::class.java)
        val throwable = HttpException(Response.error<Nothing>(404, responseErrorMock))
        val result = OpenWeatherErrorParser(throwable).parse()
        assertTrue(result is OpenWeatherResultError)
        assertTrue((result as OpenWeatherResultError).code == 404)
    }

    @Test
    internal fun `should return OpenWeatherResultError with unknown error`() {
        val throwable = Throwable(message = "unknown error")
        val result = OpenWeatherErrorParser(throwable).parse()
        assertTrue(result is OpenWeatherResultError)
        assertTrue(result.message == "unknown error")
    }

    @Test
    internal fun `should return OpenWeatherResultError with throwable`() {
        val throwable = Throwable()
        val result = OpenWeatherErrorParser(throwable).parse()
        assertTrue(result is OpenWeatherResultError)
        assertNotNull(result.exception)
    }
}