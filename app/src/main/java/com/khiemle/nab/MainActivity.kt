package com.khiemle.nab

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import com.khiemle.domain.openweather.entities.Forecast
import com.khiemle.nab.databinding.ActivityMainBinding
import com.khiemle.nab.deps.DependenciesProvider
import com.khiemle.nab.device.DeviceInfo
import com.khiemle.utilities.datetime.getCurrentTimeInMillis
import com.khiemle.utilities.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var deviceInfo: DeviceInfo

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private val forecastAdapter: ForecastAdapter by lazy {
        ForecastAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as DependenciesProvider).provideInjectionProvider().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.mainState.observe(this, MainObserver(this))
        binding.rvDailyForecast.apply {
            adapter = forecastAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        binding.textInput.doAfterTextChanged {
            it?.let { editable ->
                val content = editable.trim().toString()
                if (content.length >= 3) {
                    mainViewModel.searchV2(content, timestamp = getCurrentTimeInMillis() / 1000)
                }
            }
        }

        if (deviceInfo.isRooted) {
            val toast = Toast.makeText(this, getString(R.string.rooted_warning), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    override fun showDailyForecast(forecasts: List<Forecast>) {
        binding.pdLoading.isVisible = false
        binding.tvMessage.isVisible = false
        forecastAdapter.submitList(forecasts)
    }

    override fun showErrorMessage(message: String) {
        binding.pdLoading.isVisible = false
        binding.tvMessage.isVisible = true
        binding.tvMessage.text = message
    }

    override fun showLoading() {
        binding.pdLoading.isVisible = true
        binding.tvMessage.isVisible = false
        forecastAdapter.submitList(listOf())
    }
}