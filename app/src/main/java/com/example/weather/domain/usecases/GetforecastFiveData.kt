package com.example.weather.domain.usecases

import com.example.weather.common.constants.Resource
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastData.forecastZone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetforecastFiveData @Inject constructor(
    val repo: apiRepo
) {
    operator fun invoke(lon: Double,lat: Double,lang: String): Flow<Resource<forecastZone>> = flow {
        try {
            emit(Resource.Loading())
            val weather=repo.getForecastFiveDayData(lon,lat,lang)
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