package com.caolancode.weatherapp.data

sealed class Destination(val route: String){
    object Home: Destination("home")
    object Day: Destination("day")
}