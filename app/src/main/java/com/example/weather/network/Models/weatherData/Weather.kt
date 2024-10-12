package com.example.weather.network.Models.weatherData

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)