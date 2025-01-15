package com.example.weather.data.reposatory

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.remote.dto.API2
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
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
//        var res=mp.getCurrentWeather(lon,lat,lang,ctx)
//        Log.i("repo",mp.getCurrentWeather(lon,lat,lang,ctx).value.toString())
//        if(res.value==null)
//            res=apiImpl.getCurrentWeather(lon,lat,lang,ctx)
//        Log.i("ressss",mp.getCurrentWeather(lon,lat,lang,ctx).value.toString())
        var res= MutableLiveData<weatherZone>()
        runBlocking{
            var task=
            async{
                mp.getCurrentWeather(lon,lat,lang,ctx)
            }
            res=task.await()

        }
//        return
//        return  apiImpl.getCurrentWeather(lon,lat,lang,ctx)
        return res
    }

    override suspend fun getForecastFiveDayData(
        lon: Double,
        lat: Double,
        lang: String,
        ctx: Context
    ): MutableLiveData<forecastZone> {
       var mp=apiImpl
        mp.ctx=ctx
       var res= MutableLiveData<forecastZone>()
        runBlocking{
            var task=
            async{
                mp.getForecastForFivedays(lon,lat,lang,ctx)
            }
            res=task.await()
        }
        return res
    }

    override suspend fun getForecastDaily(
        lon: Double,
        lat: Double,
        lang: String,
        ctx: Context
    ): MutableLiveData<forecastDaily> {
        var mp=apiImpl
        mp.ctx=ctx
        var res= MutableLiveData<forecastDaily>()
        runBlocking{
            var task=
                async{
                    mp.getDailyForecast(lon,lat,lang,ctx)
                }
            res=task.await()
        }
        return res
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