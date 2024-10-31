package com.example.weather.data.reposatory

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weather.common.Tools
import com.example.weather.common.Tools.createGeneralDialog
import com.example.weather.network.Connections.Source2
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    fun getCurrentWeather(
        lon: Double,
        lat: Double,
        lang:String,
        ctx: Context
    ): MutableLiveData<weatherZone>{
        begin()
        API.getClient().getWeatherData(lon=lon,lat=lat,lang=lang).enqueue(object : Callback<weatherZone>{
            override fun onResponse(
                call: Call<weatherZone>,
                response: Response<weatherZone>
            ) {

                if(response.code()==200)
                {
                    end()
                    resApi1.value=response.body()

                }
                Log.i("getWeatherDataApi",response.body().toString())
            }

            override fun onFailure(
                call: Call<weatherZone>,
                t: Throwable
            ) {
                createGeneralDialog(
                    ctx,
                    "Problem!!",
                    "There's something went wrong",
                    "Try again",
                    "Cancel"
                ) {
                    getCurrentWeather(lon, lat, lang, ctx)
                }
            }

        }


        )
        return resApi1
    }

    //getForecastFiveDayData api
//    fun getForecastForFiveDays(
//        lon: Double,
//        lat: Double,
//        lang:String,
//        ctx: Context
//    ): MutableLiveData<forecastZone>{
//        begin()
//        API.getClient().getForecastFiveDayData(lon=lon,lat=lat,lang=lang).enqueue(object : Callback<forecastZone>{
//            override fun onResponse(
//                call: Call<forecastZone>,
//                response: Response<forecastZone>
//            ) {
//
//                if(response.code()==200)
//                {
//                    end()
//                    resApi2.value=response.body()
//                }
//                Log.i("getForecastFiveDaysApi",response.body().toString())
//            }
//
//            override fun onFailure(
//                call: Call<forecastZone>,
//                t: Throwable
//            ) {
//                createGeneralDialog(
//                    ctx,
//                    "Problem!!",
//                    "There's something went wrong",
//                    "Try again",
//                    "Cancel"
//                ) {
//                    getForecastForFiveDays(lon, lat, lang, ctx)
//                }
//            }
//
//        })
//        return resApi2
//    }

    //getForecastDaily api
    fun getDailyForecast(
        lon: Double,
        lat: Double,
        lang:String,
//        cnt:Int,
        ctx: Context
    ): MutableLiveData<forecastDaily>{
        begin()
        API.getClient().getForecastDaily(lon=lon,lat=lat,lang=lang).enqueue(object :
            Callback<forecastDaily>{
            override fun onResponse(
                call: Call<forecastDaily>,
                response: Response<forecastDaily>
            ) {

                if(response.code()==200)
                {
                    end()
                    resApi3.value=response.body()
                }
                Log.i("getForecastDailyApi",response.body().toString())
            }

            override fun onFailure(
                call: Call<forecastDaily>,
                t: Throwable
            ) {
                createGeneralDialog(
                    ctx,
                    "Problem!!",
                    "There's something went wrong",
                    "Try again",
                    "Cancel"
                ) {
                    getDailyForecast(lon, lat, lang, ctx)
                }
            }

        })
        return  resApi3
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