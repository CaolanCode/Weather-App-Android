package com.caolancode.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.components.HourSlider
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.White

@Composable
fun DayScreen(weatherViewModel: WeatherViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Title()
        Location(weatherViewModel)
        HourSlider(weatherViewModel)
        SwitchButtons(weatherViewModel)
    }
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.day_title_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    )  {
        Text(
            text = stringResource(id = R.string.day_title),
            fontWeight = FontWeight.Light,
            fontSize = dimensionResource(id = R.dimen.day_title_font_size).value.sp
        )
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.day_info_icon_size)),
            imageVector = Icons.Filled.Info,
            contentDescription = stringResource(id = R.string.day_title_information)
        )
    }
}

@Composable
fun Location(weatherViewModel: WeatherViewModel) {
    val dayNum by weatherViewModel.dayNum.collectAsState()
    val location by weatherViewModel.location.collectAsState()
    val date by weatherViewModel.dayDates[dayNum!!].collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(dimensionResource(id = R.dimen.day_location_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = location,
            color = White,
            fontSize = dimensionResource(id = R.dimen.day_location_font_size).value.sp
        )
        Text(
            text = date,
            color = White,
            fontSize = dimensionResource(id = R.dimen.day_location_font_size).value.sp
        )
    }
}

@Composable
fun SwitchButtons(weatherViewModel: WeatherViewModel) {
    val dayNum by weatherViewModel.dayNum.collectAsState()
    val shouldShowPrev = dayNum!! > 0
    val shouldShowNext = dayNum!! < 2

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.bottom_button_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Navy),
            onClick = {
                weatherViewModel.setDayNum(dayNum!! - 1)
            },
            enabled = shouldShowPrev
        ) {
            Icon(
                imageVector = Icons.Filled.NavigateBefore,
                contentDescription = stringResource(id = R.string.prev_icon_description)
            )
            Text(text = stringResource(id = R.string.prev_button))
        }
        Button(
            colors = ButtonDefaults.buttonColors(Navy),
            onClick = {
                weatherViewModel.setDayNum(dayNum!! + 1)
            },
            enabled = shouldShowNext
        ) {
            Text(text = stringResource(id = R.string.next_button))
            Icon(
                imageVector = Icons.Filled.NavigateNext,
                contentDescription = stringResource(id = R.string.prev_icon_description)
            )
        }
    }
}