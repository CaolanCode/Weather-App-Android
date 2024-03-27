package com.caolancode.weatherapp.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.RainBlue
import com.caolancode.weatherapp.presentation.ui.theme.White
import com.caolancode.weatherapp.presentation.ui.theme.Yellow

@Composable
fun HourSlider(weatherViewModel: WeatherViewModel) {
    val weatherData by weatherViewModel.weatherData.collectAsState(null)
    val dayNum by weatherViewModel.dayNum.collectAsState()
    var hour = 0
    if (dayNum == 0) {
        val currentTime = weatherData?.current?.lastUpdated?.takeLast(5) ?: "00:00"
        hour = currentTime.take(2).toIntOrNull() ?: 0
    }
    LazyRow {
        item {
            Label()
        }
        for( i in hour..23) {
            item {
                val dateTime = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.time ?: "00:00"
                val time = dateTime.takeLast(5)
                val sunrise = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.astro?.sunrise ?: "00:00"
                val sunset = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.astro?.sunset ?: "00:00"
                val icon = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.condition?.icon ?: ""
                val chanceOfRain = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.chanceOfRain ?: 0
                val windSpeed = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.windKph ?: 0.0
                val gust = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.gustKph ?: 0.0
                val temp = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.tempC ?: 0.0
                val feelsLikeTemp = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.feelsLikeC ?: 0.0
                val doublePrecipMM = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.precipMm ?: 0.0
                val precipMM = Math.round(doublePrecipMM * 10.0) / 10.0
                val humidity = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.humidity ?: 0
                val dewPoint = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.dewPointC ?: 0.0
                val pressureMb = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.pressureMb ?: 0.0
                val windDegree = weatherData?.forecast?.forecastDay?.get(dayNum!!)?.hour?.get(i)?.windDegree ?: 0

                Hour(
                    time = time,
                    sunrise = sunrise,
                    sunset = sunset,
                    icon = "https:$icon",
                    chanceOfRain = chanceOfRain,
                    windSpeed = windSpeed,
                    gust = gust,
                    temp = temp,
                    feelsLikeTemp = feelsLikeTemp,
                    precipMM = precipMM,
                    humidity = humidity,
                    dewPoint = dewPoint,
                    pressureMb = pressureMb,
                    windDegree = windDegree
                )
            }
        }
    }
}

@Composable
fun Label() {
    Column(
        modifier = Modifier.width(dimensionResource(id = R.dimen.label_width))
    ) {
        BoldLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.time_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        BoldLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.sun_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.medium_label_height))
                .background(Navy)
        ) {
            BoldLabel(
                modifier = Modifier.height(dimensionResource(id = R.dimen.medium_label_height)),
                value = stringResource(id = R.string.weather_label)
            )
        }
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        BoldLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.precip_label)
        )
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value =  stringResource(id = R.string.chance_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.large_label_height))
                .background(Navy)
        ) {
            BoldLabel(
                modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
                value = stringResource(id = R.string.wind_label)
            )
            LightLabel(
                modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
                value = stringResource(id = R.string.wind_speed_label)
            )
        }
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.gust_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        BoldLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.temp_label)
        )
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.temp_feel_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        BoldLabel(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.large_label_height))
                .background(Navy),
            value = stringResource(id = R.string.precip_label)
        )
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.mm_hour_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        BoldLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.humidity_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.dew_label)
        )
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
        LightLabel(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height)),
            value = stringResource(id = R.string.pressure_label)
        )
    }
}

@Composable
fun BoldLabel(
    modifier: Modifier = Modifier,
    value: String
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(dimensionResource(id = R.dimen.label_padding)),
        text = value,
        fontWeight = FontWeight.Bold,
        color = White
    )
}

@Composable
fun LightLabel(
    modifier: Modifier = Modifier,
    value: String
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(dimensionResource(id = R.dimen.label_padding)),
        text = value,
        fontWeight = FontWeight.Light,
        color = White
    )
}

@Composable
fun Hour(
    time: String,
    sunrise: String,
    sunset: String,
    icon: String,
    chanceOfRain: Int,
    windSpeed: Double,
    gust: Double,
    temp: Double,
    feelsLikeTemp: Double,
    precipMM: Double,
    humidity: Int,
    dewPoint: Double,
    pressureMb: Double,
    windDegree: Int
) {
    val timeHour = time.take(2).toIntOrNull()
    val sunriseHour = sunrise.take(2).toIntOrNull()
    val sunsetHour = sunset.take(2).toIntOrNull()
    val windSpeedRound = Math.round(windSpeed)
    val gustRound = Math.round(gust)
    val tempRound = Math.round(temp)
    val feelsLikeTempRound = Math.round(feelsLikeTemp)

    Column(
        modifier = Modifier.width(dimensionResource(id = R.dimen.hour_column_width))
    ) {
        TextHourItem(value = time)
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.small_label_height))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if(timeHour == sunriseHour || timeHour == (sunsetHour!! + 12) ) {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = stringResource(id = R.string.sunrise_icon),
                    tint = Yellow
                )
            }
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.medium_label_height))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(dimensionResource(id = R.dimen.medium_label_height)),
                model = icon,
                contentDescription = stringResource(id = R.string.hour_icon)
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.small_label_height))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (chanceOfRain >= 20) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.raindrop_icon_size)),
                    imageVector = Icons.Filled.WaterDrop,
                    contentDescription = stringResource(id = R.string.rain_icon),
                    tint = RainBlue
                )
            }

        }
        TextHourItem(value = "$chanceOfRain%")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Column(
            modifier = Modifier.height(dimensionResource(id = R.dimen.large_label_height))
        ) {
            Box(
                modifier = Modifier.height(dimensionResource(id = R.dimen.medium_label_height))
            ) {
                WindDirection(degrees = windDegree.toFloat(), color = Navy)
            }
            TextHourItem(value = "$windSpeedRound")
        }
        TextHourItem(value = "$gustRound")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        TextHourItem(value = "${tempRound}ºC")
        TextHourItem(value = "${feelsLikeTempRound}ºC")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.large_label_height)),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(precipMM.toFloat())
                        .fillMaxWidth()
                        .background(RainBlue)
                )
            }
        }
        TextHourItem(value = "${precipMM}mm")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        TextHourItem(value = "${humidity}%")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        TextHourItem(value = "${dewPoint}ºC")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        TextHourItem(value = "$pressureMb")
    }
}

@Composable
fun TextHourItem(value: String) {
    Text(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.small_label_height))
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically),
        text = value,
        textAlign = TextAlign.Center
    )
}