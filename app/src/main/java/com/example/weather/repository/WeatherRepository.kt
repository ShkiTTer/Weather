package com.example.weather.repository

import com.example.weather.Constants
import com.example.weather.api.ApiService
import com.example.weather.data.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiService: ApiService) {
    companion object {
        val instance by lazy { WeatherRepository(ApiService.create()) }
    }

    fun getWeather(city: String, updateWeather: (weather: WeatherData) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val weather = apiService.getWeather(city, Constants.API_KEY, Constants.WEATHER_UNITS).execute()

            withContext(Dispatchers.Main) {
                if (weather.isSuccessful) {
                    val response = weather.body()

                    if (response != null) {
                        val weatherData = WeatherData(
                            response.main.temp.toInt(),
                            response.weather[0].description,
                            response.weather[0].icon,
                            response.main.pressure,
                            response.main.humidity,
                            response.wind.speed,
                            response.wind.deg
                        )

                        updateWeather(weatherData)
                    }
                } else {

                }
            }
        }
    }
}