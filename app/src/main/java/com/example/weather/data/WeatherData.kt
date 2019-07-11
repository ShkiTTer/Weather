package com.example.weather.data

data class WeatherData(
    val temp: Int,
    val description: String,
    val icon: String,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Int,
    val windDir: Int
)