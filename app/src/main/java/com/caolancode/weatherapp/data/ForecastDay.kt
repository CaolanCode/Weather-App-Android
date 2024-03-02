package com.caolancode.weatherapp.data

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)
