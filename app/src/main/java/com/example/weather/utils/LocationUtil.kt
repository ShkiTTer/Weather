package com.example.weather.utils

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient

class LocationUtil(private val context: Context) {
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        FusedLocationProviderClient(context)
    }

    fun getLocation(updateLocation: (location: Location) -> Unit) {
        if (PermissionUtil.checkPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                updateLocation(it)
            }
        }
    }
}