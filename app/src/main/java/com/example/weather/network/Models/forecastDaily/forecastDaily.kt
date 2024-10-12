package com.example.weather.network.Models.forecastDaily

data class forecastDaily(
    val city: City,
    val cnt: Int,
    val list: List<Item0>,
)