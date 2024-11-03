package com.example.weather.data.reposatory

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.remote.dto.API2
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call
import javax.inject.Inject

class apiRepiImpl @Inject constructor(
    val api: API2,
    val apiImpl: api2Rep,
) : apiRepo {
    override suspend fun getWeatherData(
        lon: Double,
        lat: Double,
        lang: String,
        ctx: Context
    ): MutableLiveData<weatherZone> {
        val mp=apiImpl
        mp.ctx=ctx
        var res=apiImpl.getCurrentWeather(lon,lat,lang,ctx)
        if(res==null)
            res=apiImpl.getCurrentWeather(lon,lat,lang,ctx)
        return res
    }

    override suspend fun getForecastFiveDayData(
        lon: Double,
        lat: Double,
        lang: String
    ): forecastZone {
       return api.getForecastFiveDayData(lon,lat,lang)
    }

    override suspend fun getGeometrics(
        cityName: String,
        ctx: Context
        ): MutableLiveData<List<geoDataItem>> {
        val mp=apiImpl
        mp.ctx=ctx
        return mp.getCountryGeometric(cityName,ctx)
    }

}