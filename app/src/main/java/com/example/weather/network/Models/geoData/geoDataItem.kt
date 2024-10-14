package com.example.weather.network.Models.geoData

data class geoDataItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)