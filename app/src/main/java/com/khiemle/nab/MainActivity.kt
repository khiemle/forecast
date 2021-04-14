package com.khiemle.nab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.khiemle.domain.openweather.entities.Forecast
import com.khiemle.nab.databinding.ActivityMainBinding
import com.khiemle.utilities.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO: Need inject dependencies here
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.mainState.observe(this, MainObserver(this))
        binding.searchBtn.setOnClickListener {
            search(binding.textInput.text.toString())
        }
    }

    override fun showDailyForecast(forecasts: List<Forecast>) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    private fun instantSearch(query: String) {
        //TODO: Will be an extra feature if have time
    }

    private fun search(query: String) {
        mainViewModel.search(query)
    }
}