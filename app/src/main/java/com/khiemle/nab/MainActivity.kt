package com.khiemle.nab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), IMainView, IMainInteractions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showDailyForecast() {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun instantSearch(query: String) {
        TODO("Not yet implemented")
    }

    override fun search(query: String) {
        TODO("Not yet implemented")
    }
}