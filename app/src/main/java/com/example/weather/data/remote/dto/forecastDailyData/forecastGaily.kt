package com.example.weather.data.remote.dto.forecastDailyData

data class forecastGaily(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item8>,
    val message: Double
)