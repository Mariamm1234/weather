package com.example.weather.data.reposatory

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.remote.dto.API2
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call
import javax.inject.Inject

class apiRepiImpl @Inject constructor(
    val api: API2,
    val apiImpl: api2Rep,
    val ctx:Context
) : apiRepo {
    override suspend fun getWeatherData(
        lon: Double,
        lat: Double,
        lang: String
    ): MutableLiveData<weatherZone> {
        return apiImpl.getCurrentWeather(lon,lat,lang,ctx)
    }

    override suspend fun getForecastFiveDayData(
        lon: Double,
        lat: Double,
        lang: String
    ): forecastZone {
       return api.getForecastFiveDayData(lon,lat,lang)
    }

}