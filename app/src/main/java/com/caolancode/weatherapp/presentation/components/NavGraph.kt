package com.caolancode.weatherapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caolancode.weatherapp.data.Destination
import com.caolancode.weatherapp.presentation.DayScreen
import com.caolancode.weatherapp.presentation.HomeScreen

@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Destination.Home.route
        ) {
            composable(Destination.Home.route) {
                HomeScreen(
                    onNavigateToDayScreen = { navController.navigate(Destination.Day.route) }
                )
            }
            composable(Destination.Day.route) {
                DayScreen()
            }
        }
}