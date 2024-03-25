package com.caolancode.weatherapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import com.caolancode.weatherapp.data.RetrofitInterface
import com.caolancode.weatherapp.data.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WeatherViewModel: ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData = _weatherData.asStateFlow()

    private val _todayAbbrev = MutableStateFlow("")
    val todayAbbrev = _todayAbbrev.asStateFlow()
    private val _tomorrowAbbrev = MutableStateFlow("")
    val tomorrowAbbrev = _tomorrowAbbrev.asStateFlow()
    private val _twoDaysTimeAbbrev = MutableStateFlow("")
    val twoDaysTimeAbbrev = _twoDaysTimeAbbrev.asStateFlow()
    val dayAbbrevs = listOf(todayAbbrev, tomorrowAbbrev, twoDaysTimeAbbrev)

    private val _todayDayDate = MutableStateFlow("")
    val todayDayDate = _todayDayDate.asStateFlow()
    private val _tomorrowDayDate = MutableStateFlow("")
    val tomorrowDayDate = _tomorrowDayDate.asStateFlow()
    private val _twoDaysDayDate = MutableStateFlow("")
    val twoDaysDayDate = _twoDaysDayDate.asStateFlow()
    val dayDates = listOf(todayDayDate, tomorrowDayDate, twoDaysDayDate)

    private val _dayNum = MutableStateFlow<Int?>(null)
    val dayNum = _dayNum.asStateFlow()

    private val _latitude = MutableStateFlow(53.3498)
    val latitude = _latitude.asStateFlow()
    private val _longitude = MutableStateFlow(6.2603)
    val longitude = _longitude.asStateFlow()

    private val _location = MutableStateFlow(Util.DEFAULT_LOCATION)
    val location = _location.asStateFlow()

    private val _searchLocation = MutableStateFlow("")
    val searchLocation = _searchLocation.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchHistory = MutableStateFlow(listOf(""))
    val searchHistory = _searchHistory.asStateFlow()

    fun setSearchLocation(value: String) {
        _searchLocation.value = value
    }

    fun setIsSearching(value: Boolean) {
        _isSearching.value = value
    }

    fun addSearchToHistory(city: String) {
        val uniqueCities = _searchHistory.value.toSet() + city
        _searchHistory.value = uniqueCities.toList()
    }

    fun setDayNum(num: Int) {
        _dayNum.value = num
    }

    fun setLatLon(lat: Double, lon: Double) {
        _latitude.value = lat
        _longitude.value = lon
    }

    fun setWeather(data: WeatherData) {
        _weatherData.value = data
        getDayAbbreviations()
        getDayDates()
        setLocation(data.location.name)
    }

    fun setLocation(value: String) {
        _location.value = value
    }

    private fun getDayAbbreviations() {
        val today = Calendar.getInstance()
        _todayAbbrev.value = formatDay(today)
        today.add(Calendar.DAY_OF_YEAR, 1)
        _tomorrowAbbrev.value = formatDay(today)
        today.add(Calendar.DAY_OF_YEAR, 1)
        _twoDaysTimeAbbrev.value = formatDay(today)
    }

    private fun formatDay(day: Calendar): String {
        val formatter = SimpleDateFormat("EEE", Locale.ENGLISH)
        return formatter.format(day.time)
    }

    private fun getDayDates() {
        val today = weatherData.value?.forecast?.forecastDay?.get(0)?.date ?: ""
        _todayDayDate.value = formatDayDate(today)
        val tomorrow = weatherData.value?.forecast?.forecastDay?.get(1)?.date ?: ""
        _tomorrowDayDate.value = formatDayDate(tomorrow)
        val twoDays = weatherData.value?.forecast?.forecastDay?.get(2)?.date ?: ""
        _twoDaysDayDate.value = formatDayDate(twoDays)
    }

    private fun formatDayDate(dateString: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd")
        val originalDate = originalFormat.parse(dateString)
        val desiredFormat = SimpleDateFormat("EEE dd'th' MMM")
        return desiredFormat.format(originalDate)
    }

    fun callWeatherApi(location: String = Util.DEFAULT_LOCATION) {
        try {
            val call = RetrofitInterface.api.getPostByLocation(location)
            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(
                    call: Call<WeatherData>,
                    response: Response<WeatherData>
                ) {
                    setWeather(response.body()!!)
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