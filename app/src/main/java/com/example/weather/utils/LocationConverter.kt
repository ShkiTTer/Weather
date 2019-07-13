package com.example.weather.utils

import android.location.Location
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.lang.StringBuilder

object LocationConverter {
    private fun replaceCharacters(s: String): String {
        return s.replaceFirst(":", "°").replaceFirst(":", "'") + "\""
    }

    fun latitudeConvert(latitude: Double): String {
        var latitudeDegrees = Location.convert(latitude, Location.FORMAT_SECONDS)
        latitudeDegrees = replaceCharacters(latitudeDegrees)

        latitudeDegrees += if (latitude < 0) {
            "S"
        } else "N"

        return latitudeDegrees
    }

    fun longitudeConvert(longitude: Double): String {
        var longitudeDegrees = Location.convert(longitude, Location.FORMAT_SECONDS)
        longitudeDegrees = replaceCharacters(longitudeDegrees)

        longitudeDegrees += if (longitude < 0) {
            "W"
        } else "E"

        return longitudeDegrees
    }


}