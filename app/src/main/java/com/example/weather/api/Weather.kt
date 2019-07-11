package com.example.weather.api

data class Weather(
    val weather: List<WeatherDescription>,
    val main: WeatherMain,
    val wind: Wind
)