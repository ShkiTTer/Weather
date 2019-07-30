package com.example.weather.utils

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GeocoderUtil(context: Context) : CoroutineScope {
    private val geocoder = Geocoder(context)
    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun getCityName(location: LatLng, updateCity: (city: String) -> Unit) {
        launch {
            val city = geocoder.getFromLocation(location.latitude, location.longitude, 1)[0].locality

            updateCity(city)
        }
    }
}