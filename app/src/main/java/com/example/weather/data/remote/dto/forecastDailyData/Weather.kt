package com.example.weather.data.remote.dto.forecastDailyData

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)