package com.example.weather.network.Models.forecastDaily

data class City(
    val coord: Coord,
    val country: String,
    val name: String,
    val timezone: Int
)