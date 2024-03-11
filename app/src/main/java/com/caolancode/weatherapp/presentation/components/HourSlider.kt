package com.caolancode.weatherapp.presentation.components

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.RainBlue
import com.caolancode.weatherapp.presentation.ui.theme.White

@Composable
fun HourSlider() {
    LazyRow {
        for( i in 1..24) {
            item {
                if (i == 1) {
                    Label()
                } else {
                    Hour(
                        time = "14.00",
                        sunrise = "07:00",
                        sunset = "17:00",
                        icon = "https://cdn.weatherapi.com/weather/64x64/night/143.png",
                        chanceOfRain = 100,
                        windSpeed = 25,
                        gust = 14,
                        temp = 7,
                        feelsLikeTemp = 6,
                        millRain = 2,
                        humidity = 95,
                        dewPoint = 43,
                        hPa = 1000
                    )
                }
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
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_label_padding),
                horizontal = dimensionResource(id = R.dimen.horizontal_label_padding)
            ),
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
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_label_padding),
                horizontal = dimensionResource(id = R.dimen.horizontal_label_padding)
            ),
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
    windSpeed: Int,
    gust: Int,
    temp: Int,
    feelsLikeTemp: Int,
    millRain: Int,
    humidity: Int,
    dewPoint: Int,
    hPa: Int
) {
    Column(
        modifier = Modifier.width(50.dp)
    ) {
        TextHourItem(value = time)
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier.height(dimensionResource(id = R.dimen.small_label_height))
        ) {
            if(time.take(2) == sunrise.take(2) || time.take(2) == sunset.take(2) ) { 
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = stringResource(id = R.string.sunrise_icon)
                )
            }
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        AsyncImage(
            modifier = Modifier.height(dimensionResource(id = R.dimen.medium_label_height)),
            model = icon,
            contentDescription = stringResource(id = R.string.hour_icon)
        )
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
            Icon(
                imageVector = Icons.Filled.WaterDrop,
                contentDescription = stringResource(id = R.string.rain_icon),
                tint = RainBlue
            )
        }
        TextHourItem(value = "$chanceOfRain%")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier.height(dimensionResource(id = R.dimen.large_label_height))
        ) {
            Text(text = "$windSpeed")
        }
        TextHourItem(value = "$gust")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        TextHourItem(value = "${temp}ºC")
        TextHourItem(value = "${feelsLikeTemp}ºC")
        Divider(
            thickness = dimensionResource(id = R.dimen.divider_thickness),
            color = Navy
        )
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.large_label_height))
                .background(Navy)
        ) {

        }
        TextHourItem(value = "${millRain}mm")
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
        TextHourItem(value = "$hPa")
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