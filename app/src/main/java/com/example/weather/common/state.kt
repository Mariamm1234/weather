package com.example.weather.common

import com.example.weather.common.constants.Resource
import com.example.weather.network.Models.weatherData.weatherZone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class state(
    var isLoading: Boolean=false,
    var data: Flow<Resource<weatherZone>> = emptyFlow<Resource<weatherZone>>(),
    var error: String=""
)
