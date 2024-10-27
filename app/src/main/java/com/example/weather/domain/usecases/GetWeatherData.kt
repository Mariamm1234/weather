package com.example.weather.domain.usecases

import androidx.compose.ui.geometry.RoundRect
import com.example.weather.common.constants.Resource
import com.example.weather.data.remote.dto.weatherData.weather
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.weatherData.weatherZone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetWeatherData @Inject constructor(
    val repo: apiRepo
) {
    operator fun invoke(lon: Double,lat: Double,lang: String): Flow<Resource<weatherZone>> = flow {
        try {
            emit(Resource.Loading())
            val weather=repo.getWeatherData(lon,lat,lang)
            emit(Resource.Success(weather))

        }
        catch (e: HttpException)
        {
            emit(Resource.Error(e.localizedMessage?:"An error in connection"))
        }
       catch (e: IOException) {
            emit(Resource.Error("Server needs to refresh"))
        }

    }
}