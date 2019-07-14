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
            currentWeather.set(it)
        }
    }
}