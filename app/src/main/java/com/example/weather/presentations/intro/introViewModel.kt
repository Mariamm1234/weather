package com.example.weather.presentations.intro

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.common.constants.Resource
import com.example.weather.common.state
import com.example.weather.data.remote.dto.weatherData.weather
import com.example.weather.data.reposatory.apiRepiImpl
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.domain.usecases.GetGeometrics
import com.example.weather.domain.usecases.GetWeatherData
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class introViewModel @Inject constructor(
//   val getWeatherDataUsecase: GetWeatherData,
    val getGeometricsUsecase: GetGeometrics
) : ViewModel() {

//    private val _state = MutableStateFlow<Resource<weatherZone>>(Resource.Loading())
//    val state: StateFlow<Resource<weatherZone>> get() = _state

    private val _geoState = MutableStateFlow<Resource<List<geoDataItem>>>(Resource.Loading())
    val geoState: StateFlow<Resource<List<geoDataItem>>> get() =_geoState

//    fun getWeatherData(lon: Double, lat: Double, lang: String,ctx: Context) {
//        viewModelScope.launch {
//            getWeatherDataUsecase(lon, lat, lang,ctx).collect { result ->
//                Log.i("state", result.data.toString())
//
//                _state.value = result
//            }
//        }
//    }

    fun getGeoData(cityName:String,ctx:Context){
        viewModelScope.launch{
            getGeometricsUsecase(cityName,ctx).collect{
                result->
                _geoState.value=result
            }
        }
    }
}
