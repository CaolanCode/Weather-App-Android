package com.caolancode.weatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.presentation.components.Header

@Composable
fun DayScreen() {
    val location = "Singapore"
    val date = "Fri 08th March"
    Header()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.day_title))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = location)
            Text(text = date)
        }
    }
}