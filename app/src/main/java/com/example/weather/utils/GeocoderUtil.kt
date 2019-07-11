package com.example.weather.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.maps.model.LatLng

class GeocoderUtil(context: Context) {
    private val geocoder = Geocoder(context)

    fun getCityName(location: LatLng): String {
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)[0].locality
    }
}