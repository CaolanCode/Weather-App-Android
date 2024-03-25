package com.caolancode.weatherapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caolancode.weatherapp.data.Destination
import com.caolancode.weatherapp.data.WeatherData
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.DayScreen
import com.caolancode.weatherapp.presentation.HomeScreen

@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    weatherViewModel: WeatherViewModel
    ) {
        val isSearching by weatherViewModel.isSearching.collectAsState()
    
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = Destination.Home.route
        ) {
            composable(Destination.Home.route) {
                if (isSearching) {
                    LocationSearchBar(weatherViewModel = weatherViewModel)
                } else {
                    HomeScreen(
                        onNavigateToDayScreen = { navHostController.navigate(Destination.Day.route) },
                        weatherViewModel = weatherViewModel
                    )
                }
            }
            composable(Destination.Day.route) {
                DayScreen(weatherViewModel)
            }
        }
}