package com.khiemle.nab.presentation

import androidx.lifecycle.Observer

class MainObserver(private val view: IMainView): Observer<MainState> {
    override fun onChanged(state: MainState?) {
        when (state) {
            is MainState.Forecasts -> {
                view.showDailyForecast(state.items)
            }
            is MainState.ShowLoading -> {
                view.showLoading()
            }
            is MainState.ShowErrorMessage -> {
                view.showErrorMessage(message = state.msg)
            }
            else -> Unit
        }
    }


}