package com.example.weather.api.data

data class WeatherApi(
    val weather: List<WeatherDescriptionApi>,
    val main: WeatherMainApi,
    val wind: WindApi
)