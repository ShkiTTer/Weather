package com.example.weather.repository

import android.util.Log
import com.example.weather.Constants
import com.example.weather.api.ApiService
import com.example.weather.api.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiService: ApiService) {
    companion object {
        val instance by lazy { WeatherRepository(ApiService.create()) }
    }

    fun getWeather(city: String, updateWeather: (weather: Weather) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val weather = apiService.getWeather(city, Constants.API_KEY, Constants.WEATHER_UNITS).execute()

            withContext(Dispatchers.Main) {
                if (weather.isSuccessful) {
                    updateWeather(weather.body()!!)
                }
                else {
                    Log.d("ERR", weather.code().toString())
                }
            }
        }
    }
}