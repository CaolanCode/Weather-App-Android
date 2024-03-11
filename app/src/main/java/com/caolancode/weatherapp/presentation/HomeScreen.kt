package com.caolancode.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.presentation.components.DayCard
import com.caolancode.weatherapp.presentation.components.LocationMap
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDayScreen: () -> Unit
) {
    val tempIcon = "https://cdn.weatherapi.com/weather/64x64/day/143.png"
    val day = "Mon"
    val highTemp = "8ºC"
    val lowTemp = "5ºC"
    val location = "SINGAPORE"
    val locationPadding = dimensionResource(id = R.dimen.home_location_padding)
    val locationFontSize = dimensionResource(id = R.dimen.home_location_font_size).value.sp
    Column {
        LocationMap(modifier = modifier)
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
                tempIcon,
                day,
                highTemp,
                lowTemp,
                onNavigateToDayScreen
            )
            DayCard(
                tempIcon,
                day,
                highTemp,
                lowTemp,
                onNavigateToDayScreen
            )
            DayCard(
                tempIcon,
                day,
                highTemp,
                lowTemp,
                onNavigateToDayScreen
            )
        }
    }
}