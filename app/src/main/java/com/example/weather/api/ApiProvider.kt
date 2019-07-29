package com.example.weather.api

import com.example.weather.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}