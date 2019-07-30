package com.example.weather.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.NetworkState
import com.example.weather.data.WeatherData
import com.example.weather.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository.instance

    val currentWeather: ObservableField<WeatherData> = ObservableField()
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    var city = ""

    fun getWeather() {
        repository.getWeather(city) { weather, network ->
            currentWeather.set(weather)
            networkState.postValue(network)
        }
    }
}