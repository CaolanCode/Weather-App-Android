package com.caolancode.weatherapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.ui.theme.Gray
import com.caolancode.weatherapp.presentation.ui.theme.Navy

@Composable
fun DayCard(
    dayNum: Int,
    weatherViewModel: WeatherViewModel,
    onNavigateToDayScreen: () -> Unit
) {
    val weatherData by weatherViewModel.weatherData.collectAsState(null)
    val dayAbbrev by weatherViewModel.dayAbbrevs[dayNum].collectAsState()
    val today = weatherData?.forecast?.forecastDay?.get(dayNum)?.day
    val icon = today?.condition?.icon ?: ""
    val highTemp = today?.maxTempC
    val lowTemp = today?.minTempC

    Card(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.day_card_width))
            .clickable {
                onNavigateToDayScreen()
                weatherViewModel.setDayNum(dayNum)
            },
        colors = CardDefaults.cardColors(
            containerColor = Gray
        )
    ) {
        DayCardTitle(value = dayAbbrev)
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.day_icon_size))
                .align(Alignment.CenterHorizontally),
            model = "https:$icon",
            contentDescription = stringResource(id = R.string.weather_icon),
        )
        Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.day_card_temp_padding_horizontal))) {
            DayCardTemp(value = highTemp)
            Divider(color = Navy)
            DayCardTemp(value = lowTemp)
        }
    }
}

@Composable
fun DayCardTitle(value: String) {
    val titlePadding = dimensionResource(id = R.dimen.day_card_title_padding)
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Navy)
            .padding(top = titlePadding, bottom = titlePadding),
        text = value,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}

@Composable
fun DayCardTemp(value: Double?) {
    val verticalPadding = dimensionResource(id = R.dimen.day_card_temp_padding_vertical)
    val tempFontSize = dimensionResource(id = R.dimen.day_card_temp_font_size).value.sp
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        text = value.toString(),
        textAlign = TextAlign.Center,
        color = Navy,
        fontSize = tempFontSize
    )
}
