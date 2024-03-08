package com.caolancode.weatherapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caolancode.weatherapp.data.Destination
import com.caolancode.weatherapp.presentation.DayScreen
import com.caolancode.weatherapp.presentation.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(route = Destination.Home.route) { HomeScreen(Modifier, navController) }
        composable(route = Destination.Day.route) { DayScreen() }
    }
}