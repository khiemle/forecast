package com.khiemle.nab.presentation

import com.khiemle.usecases.models.Forecast

interface IMainView {
    fun showDailyForecast(forecasts: List<Forecast>)
    fun showErrorMessage(message: String)
    fun showLoading()
}