package com.example.weather.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.weather.data.WeatherData
import com.example.weather.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository.instance

    val currentWeather: ObservableField<WeatherData> = ObservableField()

    fun getWeather(city: String) {
        repository.getWeather(city) {
            val weatherData = WeatherData(
                it.main.temp.toInt(),
                it.weather[0].description,
                it.weather[0].icon,
                it.main.pressure,
                it.main.humidity,
                it.wind.speed,
                it.wind.deg
            )

            currentWeather.set(weatherData)
        }
    }
}