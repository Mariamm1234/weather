package com.example.weather.network.Models.forecastData

data class forecastZone(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item0>,
    val message: Int
)