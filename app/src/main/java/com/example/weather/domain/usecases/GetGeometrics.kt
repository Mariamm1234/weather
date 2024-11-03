package com.example.weather.domain.usecases

import android.content.Context
import com.example.weather.common.constants.Resource
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.network.Models.geoData.geoDataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGeometrics @Inject constructor(
    val repo: apiRepo
) {
    operator fun invoke(cityName: String,ctx: Context): Flow<Resource<List<geoDataItem>>> = flow{
        try {
            emit(Resource.Loading())
            val city=repo.getGeometrics(cityName,ctx).value!!
            emit(Resource.Success(city))
        }
        catch (e: Exception)
        {
            emit(Resource.Error("Server error,please try again"))
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