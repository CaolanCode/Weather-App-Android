package com.caolancode.weatherapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caolancode.weatherapp.data.RetrofitInterface
import com.caolancode.weatherapp.data.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TestButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.height(50.dp),
        onClick = {
            callApi()
        }
    ) {
        Text(text = "Call API")
    }
}

private fun callApi() {
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