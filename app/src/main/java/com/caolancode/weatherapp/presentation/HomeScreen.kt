package com.caolancode.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.components.CurrentWeather
import com.caolancode.weatherapp.presentation.components.DayCard
import com.caolancode.weatherapp.presentation.components.LocationMap
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDayScreen: () -> Unit,
    weatherViewModel: WeatherViewModel
) {
    val location by weatherViewModel.location.collectAsState()

    Column {
        LocationMap(
            modifier = modifier,
            weatherViewModel = weatherViewModel
        )
        Banner(title = location)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical =  dimensionResource(id = R.dimen.day_card_padding)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DayCard(
                dayNum = 0,
                weatherViewModel,
                onNavigateToDayScreen
            )
            DayCard(
                dayNum = 1,
                weatherViewModel,
                onNavigateToDayScreen
            )
            DayCard(
                dayNum = 2,
                weatherViewModel,
                onNavigateToDayScreen
            )
        }
        Banner(title = stringResource(id = R.string.current_banner_title))
        CurrentWeather(weatherViewModel = weatherViewModel)
    }
}

@Composable
fun Banner(title: String) {
    val bannerPadding = dimensionResource(id = R.dimen.home_banner_padding)
    val locationFontSize = dimensionResource(id = R.dimen.home_banner_font_size).value.sp
    
    Text(
        modifier = Modifier
            .background(Navy)
            .fillMaxWidth()
            .padding(bannerPadding),
        text = title,
        color = White,
        fontSize = locationFontSize
    )
}