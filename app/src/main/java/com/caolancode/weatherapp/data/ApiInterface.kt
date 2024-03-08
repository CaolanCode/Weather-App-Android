package com.caolancode.weatherapp.data

import com.caolancode.weatherapp.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("forecast.json?key=${BuildConfig.WEATHER_API_KEY}&days=3&aqi=no&alerts=no")
    fun getPostByLocation(@Query("q") location: String): Call<WeatherData>
}