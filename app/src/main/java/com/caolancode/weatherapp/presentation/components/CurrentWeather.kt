package com.caolancode.weatherapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.ui.theme.Navy

@Composable
fun CurrentWeather(weatherViewModel: WeatherViewModel) {
    val location by weatherViewModel.location.collectAsState()
    val icon by weatherViewModel.currentIcon.collectAsState()
    val temp by weatherViewModel.currentTemp.collectAsState()
    val windSpeed by weatherViewModel.currentWindSpeed.collectAsState()
    val raimMM by weatherViewModel.currentRainMM.collectAsState()
    val humidity by weatherViewModel.currentHumidity.collectAsState()
    val pressure by weatherViewModel.currentPressure.collectAsState()
    val condition by weatherViewModel.currentCondition.collectAsState()
    val windDegree by weatherViewModel.currentWindDegree.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(text = location)
            Row {
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(end = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.size(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(dimensionResource(id = R.dimen.day_icon_size)),
                            model = "https:$icon",
                            contentDescription = stringResource(id = R.string.weather_icon),
                        )
                    }
                    TitleText(title = condition)
                }
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(end = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                )  {
                    Box(
                        modifier = Modifier.size(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color.Yellow,
                                        radius = this.size.maxDimension
                                    )
                                },
                            text = Math.round(temp).toString()
                        )
                    }
                    TitleText(title = stringResource(id = R.string.current_temp_title))
                }
                Column(
                    modifier = Modifier.height(150.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                )  {
                    Box(
                        modifier = Modifier.size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        WindDirection(
                            degrees = windDegree.toFloat(),
                            color = Navy
                        )
                    }
                    TitleText(title = Math.round(windSpeed).toString())
                    TitleText(title = stringResource(id = R.string.current_wind_title))
                }
            }
        }
        Column(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.current_rain_title))
            Text(text = "${raimMM}mm")
            Divider()
            Text(text = stringResource(id = R.string.current_humidity_title))
            Text(text = "${humidity}%")
            Divider()
            Text(text = stringResource(id = R.string.current_pressure_title))
            Text(text = "${pressure}hPa")
        }
    }
}

@Composable
fun TitleText(title: String) {
    Text(
        modifier = Modifier.width(70.dp),
        text = title,
        textAlign = TextAlign.Center
    )
}