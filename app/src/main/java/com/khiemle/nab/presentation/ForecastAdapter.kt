package com.khiemle.nab.presentation

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khiemle.domain.models.Forecast
import com.khiemle.nab.databinding.ItemForecastBinding
import com.khiemle.utilities.datetime.convertTimestampToDisplayDate

class ForecastAdapter : ListAdapter<Forecast, RecyclerView.ViewHolder>(ForecastListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = (parent.context as Activity).layoutInflater
        return ForecastItemVH.onCreateViewHolder(layoutInflater = layoutInflater, parent = parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ForecastItemVH).onBind(getItem(position))
    }

    private class ForecastListDiffCallback : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem.timeStamp == newItem.timeStamp
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem == newItem
        }
    }
}

internal class ForecastItemVH(private val containerView: ItemForecastBinding) :
    RecyclerView.ViewHolder(containerView.root) {

    fun onBind(forecast: Forecast) {
        containerView.apply {
            tvDate.text = convertTimestampToDisplayDate(forecast.timeStamp)
            tvAverageTemperature.text = forecast.averageTemperature
            tvPressure.text = forecast.pressure
            tvHumidity.text = forecast.humidity
            tvDescription.text = forecast.description
        }
    }

    companion object {
        fun onCreateViewHolder(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): RecyclerView.ViewHolder {
            val binding: ItemForecastBinding = ItemForecastBinding.inflate(layoutInflater, parent,false)
            return ForecastItemVH(binding)
        }
    }

}