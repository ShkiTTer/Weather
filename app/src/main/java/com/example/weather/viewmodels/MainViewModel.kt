package com.example.weather.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.weather.data.CityData
import com.example.weather.utils.GeocoderUtil
import com.example.weather.utils.LocationConventer
import com.google.android.gms.maps.model.LatLng

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val context = app.applicationContext
    private val geocoderUtil = GeocoderUtil(context)

    var changedCity: ObservableField<CityData> = ObservableField()

    fun changePoint(location: LatLng) {
        val place = geocoderUtil.getCityName(location)
        val coords =
            LocationConventer.latituteConvert(location.latitude) + " " + LocationConventer.longitudeConvert(location.longitude)
        val city = CityData(place, coords)
        changedCity.set(city)
    }
}