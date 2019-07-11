package com.example.weather.utils

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener

class LocationUtil(private val context: Context) {
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        FusedLocationProviderClient(context)
    }
}