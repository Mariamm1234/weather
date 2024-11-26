package com.example.weather.presentations.home.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.constants.Resource
import com.example.weather.domain.usecases.GetWeatherData
import com.example.weather.network.Models.weatherData.weatherZone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    val getWeatherDataUsecase: GetWeatherData
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<weatherZone>>(Resource.Loading())
    val state: StateFlow<Resource<weatherZone>> get() = _state
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    fun getWeatherData(lon: Double, lat: Double, lang: String, ctx: Context) {
        viewModelScope.launch {
            getWeatherDataUsecase(lon, lat, lang, ctx).collect { result ->
                Log.i("state", result.data.toString())

                _state.value = result
            }
        }
    }
}