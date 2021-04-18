package com.khiemle.nab

import com.khiemle.nab.presentation.IMainView
import com.khiemle.nab.presentation.MainObserver
import com.khiemle.nab.presentation.MainState
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test

internal class MainObserverTest {
    private val mainViewMock: IMainView = mock()
    private lateinit var mainObserver: MainObserver

    @Before
    fun setUp() {
        mainObserver = MainObserver(mainViewMock)
    }

    @Test
    internal fun `should call view showDailyForecast func`() {
        mainObserver.onChanged(MainState.ShowLoading)
        Verify on mainViewMock that mainViewMock.showLoading() was called
    }

    @Test
    internal fun `should call view showErrorMessage func`() {
        mainObserver.onChanged(MainState.ShowErrorMessage(msg = "error message"))
        Verify on mainViewMock that mainViewMock.showErrorMessage(message = "error message") was called
    }

    @Test
    internal fun `should call view showLoading func`() {
        val forecasts = getValidForeCast()
        mainObserver.onChanged(MainState.Forecasts(forecasts))
        Verify on mainViewMock that mainViewMock.showDailyForecast(forecasts) was called
    }
}