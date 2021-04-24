package com.khiemle.nab.presentation

import com.khiemle.domain.models.Forecast

interface IMainView {
    fun showDailyForecast(forecasts: List<Forecast>)
    fun showErrorMessage(message: String)
    fun showLoading()
}