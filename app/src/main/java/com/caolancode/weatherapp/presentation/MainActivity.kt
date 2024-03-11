package com.caolancode.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caolancode.weatherapp.presentation.components.Header
import com.caolancode.weatherapp.presentation.components.SetupNavGraph

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            Scaffold(
                topBar = {
                    Header()
                }
            ) { innerPadding ->
                SetupNavGraph(modifier = Modifier.padding(innerPadding), navController)
            }
        }
    }
}
