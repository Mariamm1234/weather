package com.example.weather.data.remote.dto.forecastData

data class forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item0>,
    val message: Int
)