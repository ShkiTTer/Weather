package com.example.weather.repository

import com.example.weather.Constants
import com.example.weather.NetworkState
import com.example.weather.api.ApiService
import com.example.weather.api.Weather
import com.example.weather.data.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherRepository(private val apiService: ApiService) {
    companion object {
        val instance by lazy { WeatherRepository(ApiService.create()) }
    }

    fun getWeather(city: String, updateWeather: (weather: WeatherData?, networkState: NetworkState) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var weather: Response<Weather>

            try {
                weather = apiService.getWeather(city, Constants.API_KEY, Constants.WEATHER_UNITS).execute()
            } catch (t: Throwable) {
                updateWeather(null, NetworkState.ERROR)
                return@launch
            }

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

                    updateWeather(weatherData, NetworkState.SUCCESS)
                }
            } else {
                updateWeather(null, NetworkState.ERROR)
            }

        }
    }
}