package com.example.weather.viewmodels

import android.app.Application
import android.location.Location
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.CityData
import com.example.weather.utils.GeocoderUtil
import com.example.weather.utils.LocationConverter
import com.example.weather.utils.LocationUtil
import com.google.android.gms.maps.model.LatLng

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val context = app.applicationContext
    private val geocoderUtil = GeocoderUtil(context)
    private val locationUtil = LocationUtil(context)

    var changedCity: ObservableField<CityData> = ObservableField()
    var currentLocation = MutableLiveData<Location>()

    fun changePoint(location: LatLng) {
        val city = CityData(
            geocoderUtil.getCityName(location),
            LocationConverter.latitudeConvert(location.latitude),
            LocationConverter.longitudeConvert(location.longitude)
        )

        changedCity.set(city)
    }

    fun getLocation() {
        locationUtil.getLocation {
            currentLocation.value = it
        }
    }
}