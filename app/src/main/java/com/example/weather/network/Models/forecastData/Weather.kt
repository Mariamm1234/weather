package com.example.weather.network.Models.forecastData

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)