package com.example.weather.data

data class CityData(
    val name: String,
    val latitude: String,
    val longitude: String,
    val isChanged: Boolean = name.isNotEmpty()
)