package com.example.weather.domain.reposatories

import androidx.lifecycle.MutableLiveData
import com.example.weather.common.constants.Resource
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call

//try with only these
interface apiRepo {
    suspend fun getWeatherData(lon: Double, lat: Double, lang: String): MutableLiveData<weatherZone>
    suspend fun getForecastFiveDayData( lon: Double, lat: Double, lang: String): forecastZone
}