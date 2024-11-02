package com.example.weather.di

import com.example.weather.data.reposatory.apiRepiImpl
import com.example.weather.domain.reposatories.apiRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ProvideApiRepo {

    @Binds
    abstract fun bindRepoApi(repo: apiRepiImpl): apiRepo
}