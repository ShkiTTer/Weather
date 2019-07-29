package com.example.weather.repository

import com.example.weather.ApiConstants
import com.example.weather.NetworkState
import com.example.weather.api.ApiService
import com.example.weather.api.data.WeatherApi
import com.example.weather.data.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class WeatherRepository(private val apiService: ApiService) : CoroutineScope {
    companion object {
        val instance by lazy { WeatherRepository(ApiService.create()) }
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun getWeather(city: String, updateWeather: (weather: WeatherData?, networkState: NetworkState) -> Unit) {
        launch {
            val weather: Response<WeatherApi>

            try {
                weather = apiService.getWeather(city, ApiConstants.WEATHER_API, ApiConstants.WEATHER_UNITS).execute()
            } catch (t: Throwable) {
                t.printStackTrace()
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