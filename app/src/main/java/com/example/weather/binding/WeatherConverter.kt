package com.example.weather.binding

import com.example.weather.Constants

object WeatherConverter {
    private val windDirections =
        listOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW")

    private const val EMPTY_STRING = ""

    @JvmStatic
    fun convertDescription(description: String?): String {
        if (description == null) return EMPTY_STRING

        val words = description.split(' ').toMutableList()

        words.withIndex().forEach {
            val value = it.value
            val index = it.index

            val new = value.replace(value.first(), value.first().toUpperCase())
            words[index] = new
        }

        return words.joinToString("\n")
    }

    @JvmStatic
    fun convertWindDir(deg: Double?): String {
        if (deg == null) return EMPTY_STRING

        val value = (deg / 22.5 + .5).toInt()
        return windDirections[value % 16]
    }

    @JvmStatic
    fun convertPressure(pressure: Int?): Double {
        if (pressure == null) return .0

        return pressure * Constants.HPA_TO_MMHG
    }
}