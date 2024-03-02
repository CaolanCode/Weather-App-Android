package com.caolancode.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.caolancode.weatherapp.data.RetrofitInterface
import com.caolancode.weatherapp.data.WeatherData
import com.caolancode.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(
                        onClick = {
                            callApi()
                        }
                    ) {
                        Text(text = "Call API")
                    }
                }
            }
        }
    }

    private fun callApi() {
        lifecycleScope.launch {
            try {
                val call = RetrofitInterface.api.getPostByLocation("London")
                call.enqueue(object : Callback<WeatherData> {
                    override fun onResponse(
                        call: Call<WeatherData>,
                        response: Response<WeatherData>
                    ) {
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}