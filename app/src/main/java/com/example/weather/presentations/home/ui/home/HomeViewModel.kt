package com.example.weather.presentations.home.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.constants.Resource
import com.example.weather.domain.usecases.GetDailyForecast
import com.example.weather.domain.usecases.GetGeometrics
import com.example.weather.domain.usecases.GetWeatherData
import com.example.weather.domain.usecases.GetforecastFiveData
import com.example.weather.network.Models.forecastDaily.forecastDaily
import com.example.weather.network.Models.forecastData.forecastZone
import com.example.weather.network.Models.geoData.geoDataItem
import com.example.weather.network.Models.weatherData.weatherZone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    val getWeatherDataUsecase: GetWeatherData,
    val getDailyForecastUsecase: GetDailyForecast,
    val getFiveDayForecastUsecase: GetforecastFiveData,
    val getGeoDataUsecase: GetGeometrics
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


    private val _forecast = MutableStateFlow<Resource<forecastDaily>>(Resource.Loading())
    val forecast: StateFlow<Resource<forecastDaily>> get() = _forecast

    fun getDailyForecast(lon: Double,lat: Double,lang: String,ctx: Context){
        viewModelScope.launch{
            getDailyForecastUsecase(lon,lat,lang, ctx).collect{ result->
                _forecast.value=result

            }
        }
    }


    private val _daily = MutableStateFlow<Resource<forecastZone>>(Resource.Loading())
    val daily: StateFlow<Resource<forecastZone>> get() = _daily

    fun getFiveDayForecast(lon: Double,lat: Double,lang: String,ctx: Context){
        viewModelScope.launch{
            getFiveDayForecastUsecase(lon,lat,lang,ctx).collect{
                result->
                _daily.value=result
            }
        }
    }
    private val _geoState = MutableStateFlow<Resource<List<geoDataItem>>>(Resource.Loading())
    val geoState: StateFlow<Resource<List<geoDataItem>>> get() =_geoState

    fun getGeoData(cityName:String,ctx:Context){
        viewModelScope.launch{
            getGeoDataUsecase(cityName,ctx).collect{
                    result->
                _geoState.value=result
            }
        }
    }

}