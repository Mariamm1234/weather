package com.example.weather.data.remote.dto.Geo

data class geoCodingItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)