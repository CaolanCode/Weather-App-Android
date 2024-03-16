package com.caolancode.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caolancode.weatherapp.data.RetrofitInterface
import com.caolancode.weatherapp.data.WeatherData
import com.caolancode.weatherapp.domain.Util
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.caolancode.weatherapp.presentation.components.Header
import com.caolancode.weatherapp.presentation.components.SetupNavGraph
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = WeatherViewModel()

        setContent {
            navHostController = rememberNavController()
            Scaffold(
                topBar = {
                    Header()
                }
            ) { innerPadding ->
                SetupNavGraph(modifier = Modifier.padding(innerPadding), navHostController, weatherViewModel)
            }
        }

        lifecycleScope.launch {
            callWeatherApi()
        }

    }

    private suspend fun callWeatherApi(location: String = Util.DEFAULT_LOCATION) {
        try {
            val call = RetrofitInterface.api.getPostByLocation(location)
            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(
                    call: Call<WeatherData>,
                    response: Response<WeatherData>
                ) {
                    weatherViewModel.updateWeather(response.body()!!)
                    Log.d("TAG", "onResponse: ${response.body()}")
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
        } catch (e: Exception) {
            Log.d("TAG", "error: ${e.message}")
        }
    }
}
