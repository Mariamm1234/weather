package com.example.weather.network.Models.forecastData

data class City(
    val coord: Coord,
    val country: String,
    val name: String,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)