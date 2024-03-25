package com.caolancode.weatherapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.caolancode.weatherapp.domain.WeatherViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchBar(weatherViewModel: WeatherViewModel) {
    val searchLocation by weatherViewModel.searchLocation.collectAsState()
    val isSearching by weatherViewModel.isSearching.collectAsState()
    val searchHistory by weatherViewModel.searchHistory.collectAsState()

    SearchBar(
        query = searchLocation,
        onQueryChange = {
            weatherViewModel.setSearchLocation(it)
        },
        onSearch = {
            weatherViewModel.setIsSearching(false)
            weatherViewModel.addSearchToHistory(it)
            weatherViewModel.callWeatherApi(it)
            weatherViewModel.setSearchLocation("")
        },
        active = isSearching,
        onActiveChange = {
            weatherViewModel.setIsSearching(it)
        },
        placeholder = {
            Text(text = "Location")
        },
        trailingIcon = {
            if (isSearching) {
                Icon(
                    modifier = Modifier.clickable {
                        if (searchLocation.isNotEmpty()) {
                            weatherViewModel.setSearchLocation("")
                        } else {
                            weatherViewModel.setIsSearching(false)
                        }
                    },
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Exit Search"
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Location Search"
                )
            }
        }
    ) {
        searchHistory.forEach {
            if (it.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .clickable {
                            weatherViewModel.setSearchLocation(it)
                        }
                ) {
                    Icon(imageVector = Icons.Filled.History,
                        contentDescription = "Search history"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = it)
                }
            }
        }
    }
}