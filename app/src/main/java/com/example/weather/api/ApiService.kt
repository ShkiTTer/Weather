package com.example.weather.api

import com.example.weather.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("weather")
    fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String): Call<Weather>
}