package com.example.weather.network.Connections

import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.weatherData.weatherZone
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    private val api: String
        get() = "3fd2e78bf3537dedc8f32cb47c2d44eb"


    @GET("weather")
    fun getWeatherData(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api
    ): Call<weatherZone>

    @GET("forecast")
     fun getForecastFiveDayData(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api
    ): Call<forecastZone>

    @GET("forecast/daily")
     fun getForecastDaily(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String=api,
//        @Query("cnt") cnt:Int
    ): Call<forecastDaily>
}