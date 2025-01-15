package com.example.weather.data.reposatory

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.startup.StartupLogger.e
import com.example.weather.common.Tools
import com.example.weather.common.Tools.createGeneralDialog
import com.example.weather.network.Connections.Source2
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@SuppressLint("StaticFieldLeak")
object api2Rep {
    val API= Source2
    lateinit var ctx :Context
    var resApi1 =MutableLiveData<weatherZone>()
    var resApi2 =MutableLiveData<forecastZone>()
    var resApi3 =MutableLiveData<forecastDaily>()
    var resApi4 =MutableLiveData<List<geoDataItem>>()
    private  fun begin()
    {
        Tools.Start(ctx)
    }
    private fun end()
    {
        Tools.End()
    }
    //return to access response un activity
    // getWeatherData api

    suspend fun getCurrentWeather(
                lon: Double,
        lat: Double,
        lang:String,
        ctx: Context

    ): MutableLiveData<weatherZone>{
        return suspendCoroutine {  coro->
            begin()
        API.getClient().getWeatherData(lon,lat,lang).enqueue(object : Callback<weatherZone>{
            override fun onResponse(
                call: Call<weatherZone>,
                response: Response<weatherZone>
            ) {
                end()
                if (response.isSuccessful) {
                    resApi1.postValue(response.body())
                    coro.resume(resApi1)
                } else {
                    Log.e("getWeatherDataApi", "Error code: ${response.code()}")
//                    coro.resume(null)
                    coro.resume(null as MutableLiveData<weatherZone>)
                }
            }

            override fun onFailure(
                call: Call<weatherZone>,
                t: Throwable
            ) {
                Log.e("getWeatherDataApi", "Error: ${t.message}")
                createGeneralDialog(
                    ctx,
                    "Problem!!",
                    "There's something went wrong",
                    "Try again",
                    "Cancel"
                ) {
                    // Optional retry logic
                }
//                coro.resume(null)
                coro.resumeWithException(t)
            }

        })}
    }

    //forecast five day api
    suspend fun getForecastForFivedays(

        lon: Double,
        lat: Double,
        lang: String,
        ctx: Context
    ): MutableLiveData<forecastZone> {
        return suspendCoroutine { coro->
            begin()
        API.getClient().getForecastFiveDayData(lon, lat, lang)
            .enqueue(object : Callback<forecastZone> {
                override fun onResponse(
                    call: Call<forecastZone>,
                    response: Response<forecastZone>
                ) {
end()
                    if (response.isSuccessful) {
                        resApi2.postValue(response.body())
                        coro.resume(resApi2)
                    } else {
                        Log.e("getWeatherDataApi", "Error code: ${response.code()}")
//                    coro.resume(null)
                        coro.resume(null as MutableLiveData<forecastZone>)
                    }

                }

                override fun onFailure(
                    call: Call<forecastZone>,
                    t: Throwable
                ) {
                    Log.e("getWeatherDataApi", "Error: ${t.message}")
                    createGeneralDialog(
                        ctx,
                        "Problem!!",
                        "There's something went wrong",
                        "Try again",
                        "Cancel"
                    ) {
                        // Optional retry logic
                    }
//                coro.resume(null)
                    coro.resumeWithException(t)
                }
            })
    }
    }


    //getForecastDaily api
suspend fun getDailyForecast(
        lon: Double,
        lat: Double,
        lang:String,
//        cnt:Int,
        ctx: Context
    ): MutableLiveData<forecastDaily>{
        return suspendCoroutine {  coro->
        begin()
        API.getClient().getForecastDaily(lon=lon,lat=lat,lang=lang).enqueue(object :
            Callback<forecastDaily>{
            override fun onResponse(
                call: Call<forecastDaily>,
                response: Response<forecastDaily>
            ) {

                end()
                if (response.isSuccessful) {
                    resApi3.postValue(response.body())
                    coro.resume(resApi3)
                } else {
                    Log.e("getWeatherDataApi", "Error code: ${response.code()}")
//                    coro.resume(null)
                    coro.resume(null as MutableLiveData<forecastDaily>)
                }
            }

            override fun onFailure(
                call: Call<forecastDaily>,
                t: Throwable
            ) {
                Log.e("getWeatherDataApi", "Error: ${t.message}")
                createGeneralDialog(
                    ctx,
                    "Problem!!",
                    "There's something went wrong",
                    "Try again",
                    "Cancel"
                ) {
                    // Optional retry logic
                }
//                coro.resume(null)
                coro.resumeWithException(t)
            }

        })}
    }

    fun getCountryGeometric(
        cityName:String,
        ctx: Context
    ): MutableLiveData<List<geoDataItem>>{
        begin()
        API.getClient().getCountryGeometric(cityName=cityName).enqueue(object : Callback<List<geoDataItem>>{
            override fun onResponse(
                call: Call<List<geoDataItem>>,
                response: Response<List<geoDataItem>>
            ) {
                if(response.code()==200)
                {
                    end()
                    resApi4.value= response.body() as List<geoDataItem>
                }
                Log.i("getCountryGeometrics",response.body().toString())
            }

            override fun onFailure(
                call: Call<List<geoDataItem>>,
                t: Throwable
            ) {
                createGeneralDialog(
                    ctx,
                    "Problem!!",
                    "There's something went wrong",
                    "Try again",
                    "Cancel"
                ) {
                    getCountryGeometric(cityName,ctx)
                }
            }


        })
        return resApi4
    }
}