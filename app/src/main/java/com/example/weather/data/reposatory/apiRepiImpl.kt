package com.example.weather.data.reposatory

import com.example.weather.data.remote.dto.API2
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call
import javax.inject.Inject

class apiRepiImpl @Inject constructor(
    val api: API2
) : apiRepo {
    override suspend fun getWeatherData(
        lon: Double,
        lat: Double,
        lang: String
    ): weatherZone {
        return api.getWeatherData(lon,lat,lang)
    }

    override suspend fun getForecastFiveDayData(
        lon: Double,
        lat: Double,
        lang: String
    ): forecastZone {
       return api.getForecastFiveDayData(lon,lat,lang)
    }

}