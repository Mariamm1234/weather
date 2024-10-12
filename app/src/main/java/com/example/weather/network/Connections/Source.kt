package com.example.weather.network.Connections

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//connect all together
object Source {
    fun getClient(): ApiServices
    {
        var retrofit: Retrofit?=null
        //search for iys meaning
        val gson = GsonBuilder()
            .setLenient().create()
        //chck to create retrofit if not exist
        if(retrofit==null)
        {
            retrofit= Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
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
        return retrofit!!.create(ApiServices::class.java)
    }
}