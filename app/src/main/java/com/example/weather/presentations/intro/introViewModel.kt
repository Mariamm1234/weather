package com.example.weather.presentations.intro

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.constants.Resource
import com.example.weather.common.state
import com.example.weather.data.remote.dto.weatherData.weather
import com.example.weather.domain.reposatories.apiRepo
import com.example.weather.domain.usecases.GetWeatherData
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
    private val getWeatherDataUsecase: GetWeatherData,

): ViewModel() {
//    private val _state= MutableLiveData<Resource<weatherZone>>()
//    val state: LiveData<Resource<weatherZone>> =_state
companion object{

}
private val _state = MutableStateFlow<Resource<weatherZone>>(Resource.Loading())
    val state: StateFlow<Resource<weatherZone>> = _state

   fun getWeatherData(lon: Double,lat: Double,lang: String){
       var res: weatherZone
     viewModelScope.launch(Dispatchers.IO)
     {
       getWeatherDataUsecase(lon,lat,lang).collect{
           result->
           _state.value=result
       }

//      return@launch
    }
 }
}