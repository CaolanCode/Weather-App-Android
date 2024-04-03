package com.caolancode.weatherapp.domain

import androidx.compose.ui.graphics.Color
import com.caolancode.weatherapp.presentation.ui.theme.Orange
import com.caolancode.weatherapp.presentation.ui.theme.RainBlue
import com.caolancode.weatherapp.presentation.ui.theme.Red
import com.caolancode.weatherapp.presentation.ui.theme.White
import com.caolancode.weatherapp.presentation.ui.theme.Yellow

object Util {
    const val BASE_URL = "https://api.weatherapi.com/v1/"
    const val DEFAULT_LOCATION = "Dublin"
    const val PERMISSION_REQUEST_ACCESS_LOCATION = 100

    fun getTempColor(temp: Long): Color {
        return when (temp) {
            in Int.MIN_VALUE..2 -> RainBlue
            in 3..7 -> White
            in 8..13 -> Yellow
            in 14..18 -> Orange
            else -> Red
        }
    }
}