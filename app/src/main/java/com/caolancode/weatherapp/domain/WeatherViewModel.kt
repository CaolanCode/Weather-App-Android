package com.caolancode.weatherapp.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.caolancode.weatherapp.data.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
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

    fun updateDayNum(num: Int) {
        _dayNum.value = num
    }

    fun updateWeather(data: WeatherData) {
        _weatherData.value = data
        getDayAbbreviations()
        getDayDates()
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
}