package com.example.weather.data.remote.dto

import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API2 {
    private val api: String
        get() = "3fd2e78bf3537dedc8f32cb47c2d44eb"


    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api
    ): Call< weatherZone>

    @GET("data/2.5/forecast")
    fun getForecastFiveDayData(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api
    ): Call<forecastZone>

    @GET("data/2.5/forecast/daily")
    fun getForecastDaily(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api,
//        @Query("cnt") cnt:Int
    ): Call<forecastDaily>

    @GET("geo/1.0/direct")
    fun getCountryGeometric(
        @Query("q") cityName: String,
//         @Query("limit")limit: Int=5,
        @Query("appid") apiKey: String=api,

        ): Call<List<geoDataItem>>
}