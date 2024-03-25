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
import androidx.compose.ui.unit.sp
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
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
    val locationPadding = dimensionResource(id = R.dimen.home_location_padding)
    val locationFontSize = dimensionResource(id = R.dimen.home_location_font_size).value.sp
    val location by weatherViewModel.location.collectAsState()

    Column {
        LocationMap(
            modifier = modifier,
            weatherViewModel = weatherViewModel
        )
        Text(
            modifier = Modifier
                .background(Navy)
                .fillMaxWidth()
                .padding(locationPadding),
            text = location,
            color = White,
            fontSize = locationFontSize
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.day_card_top_padding)),
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
    }
}