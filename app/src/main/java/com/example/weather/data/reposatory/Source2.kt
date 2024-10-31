package com.example.weather.network.Connections

import com.example.weather.data.remote.dto.API2
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//connect all together
object Source2 {
    fun getClient(): API2
    {
        var retrofit: Retrofit?=null
        //search for iys meaning
        val gson = GsonBuilder()
            .setLenient().create()
        //chck to create retrofit if not exist
        if(retrofit==null)
        {
            retrofit= Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        /*
        * instead of this line:
        * val apiServices=ApiServices.buildServices()
        *
        * fun buildServices():ApiServices{
        * return retrofit.create(ApiServices::class.java)
        * }
        * */
        return retrofit!!.create(API2::class.java)
    }
}