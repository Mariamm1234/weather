package com.example.weather.network.Models.weatherData

data class weatherZone(
    val clouds: Clouds,
    val coord: Coord,
    val main: Main,
    val name: String,
    val rain: Rain,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)