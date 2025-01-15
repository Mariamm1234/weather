package com.example.weather.domain.usecases

import android.content.Context
import android.util.Log
import com.example.weather.common.constants.Resource
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.forecastData.forecastZone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetforecastFiveData @Inject constructor(
    val repo: apiRepo
) {
    operator fun invoke(lon: Double,lat: Double,lang: String,ctx: Context): Flow<Resource<forecastZone>> = flow {
        try {
            emit(Resource.Loading())
            Log.i("weather2", repo.getForecastFiveDayData(lon,lat,lang,ctx).value.toString())
            var weather=repo.getForecastFiveDayData(lon,lat,lang,ctx).value
            Log.i("weather", weather.toString())
            emit(Resource.Success(weather))

        } catch (e: Exception) {
            Log.i("eccept", e.toString())
            emit(Resource.Error("Server error,please try again"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"An error in connection"))
        } catch (e: IOException) {
            emit(Resource.Error("Server needs to refresh"))
        }
    }.flowOn(Dispatchers.IO) as Flow<Resource<forecastZone>>

}