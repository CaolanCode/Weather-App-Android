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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.data.Destination
import com.caolancode.weatherapp.presentation.ui.theme.Gray
import com.caolancode.weatherapp.presentation.ui.theme.Navy

@Composable
fun DayCard(
    tempIcon: String,
    day: String,
    highTemp: String,
    lowTemp: String,
    onNavigateToDayScreen: () -> Unit
) {
    val cardWidth = dimensionResource(id = R.dimen.day_card_width)
    val tempPadding = dimensionResource(id = R.dimen.day_card_temp_padding_horizontal)
    Card(
        modifier = Modifier
            .width(cardWidth)
            .clickable { onNavigateToDayScreen() },
        colors = CardDefaults.cardColors(
            containerColor = Gray
        )
    ) {
        DayCardTitle(value = day)
        AsyncImage(
            modifier = Modifier.size(cardWidth),
            model = tempIcon,
            contentDescription = stringResource(id = R.string.weather_icon),
        )
        Column(modifier = Modifier.padding(horizontal = tempPadding)) {
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
fun DayCardTemp(value: String) {
    val verticalPadding = dimensionResource(id = R.dimen.day_card_temp_padding_vertical)
    val tempFontSize = dimensionResource(id = R.dimen.day_card_temp_font_size).value.sp
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        text = value,
        textAlign = TextAlign.Center,
        color = Navy,
        fontSize = tempFontSize
    )
}
