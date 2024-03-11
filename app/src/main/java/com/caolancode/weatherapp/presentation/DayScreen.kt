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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.presentation.components.Header
import com.caolancode.weatherapp.presentation.components.HourSlider
import com.caolancode.weatherapp.presentation.ui.theme.Navy
import com.caolancode.weatherapp.presentation.ui.theme.White

@Composable
fun DayScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Title()
        Location()
        HourSlider()
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
fun Location() {
    val location = "Singapore"
    val date = "Fri 08th March"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(
                vertical = dimensionResource(
                    id = R.dimen.day_location_padding_vertical
                ),
                horizontal = dimensionResource(
                    id = R.dimen.day_location_padding_horizontal
                )
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = location,
            color = White,
            fontSize = dimensionResource(id = R.dimen.day_location_font_size).value.sp
        )
        Text(text = date,
            color = White,
            fontSize = dimensionResource(id = R.dimen.day_location_font_size).value.sp
        )
    }
}