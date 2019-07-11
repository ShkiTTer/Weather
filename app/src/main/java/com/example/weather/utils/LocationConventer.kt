package com.example.weather.utils

import android.location.Location
import java.lang.StringBuilder

object LocationConventer {
    fun latituteConvert(latitude: Double): String {
        val builder = StringBuilder()
        val latitudeDegrees = Location.convert(latitude, Location.FORMAT_SECONDS)
        val latitudeSplit = latitudeDegrees.split(":")

        builder.append(latitudeSplit[0])
            .append("°")
            .append(latitudeDegrees[1])
            .append("'")
            .append(latitudeDegrees[2])
            .append("\"")

        if (latitude < 0) {
            builder.append("S")
        }
        else builder.append("N")

        return builder.toString()
    }

    fun longitudeConvert(longitude: Double): String {
        val builder = StringBuilder()
        val longitudeDegrees = Location.convert(longitude, Location.FORMAT_SECONDS)
        val longitudeSplit = longitudeDegrees.split(":")

        builder.append(longitudeSplit[0])
            .append("°")
            .append(longitudeSplit[1])
            .append("'")
            .append(longitudeSplit[2])
            .append("\"")

        if (longitude < 0) {
            builder.append("W")
        }
        else builder.append("E")

        return builder.toString()
    }
}