package com.example.weather.di

import android.content.Context
import com.example.weather.common.Location.weatherApp
import com.example.weather.data.remote.dto.API2
import com.example.weather.data.reposatory.apiRepiImpl
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.domain.usecases.GetWeatherData
import com.example.weather.network.Connections.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app:Context):weatherApp{
        return app as weatherApp
    }

    @Singleton
    @Provides
    fun provideRetrofit(client:OkHttpClient): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit): ApiServices
    {
        return retrofit.create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient
    {
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val okHttpClient= OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor
    {
        val httpLoggingInterceptor= HttpLoggingInterceptor()
        httpLoggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideApiRepo(api: API2):apiRepo
    {
        return apiRepiImpl(api)
    }

    @Singleton
    @Provides
    fun provideUseCase(usecase: GetWeatherData): GetWeatherData {
        return usecase
    }
}