package com.example.weather.common.Location

import android.location.Location
import androidx.compose.ui.text.intl.Locale
import kotlinx.coroutines.flow.Flow

interface locationClient {
    fun getLocationUpdate(interval:Long): Flow<Location>

    class locationException (message:String): Exception()
}