package com.example.weather.common.Location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
//to get location data if is granted and update it if needed
//1-request location
//2.use it with callback
//3.update it
class DefaultLocationClient (
    private val ctx:Context,
    private val client: FusedLocationProviderClient
): locationClient {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdate(interval: Long): Flow<Location> {
        return callbackFlow {
//check permission if is granted or not and ask about it
            if(!ctx.hasLocationPermission())
                throw locationClient.locationException("Location access is invalid")



   // if ti's granted
   //check if you can catch location
   //by checking if GPS or NETWORK is enabled
            val locationManager=ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val hasGpsEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val hasNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            //if not enabled throw exception
            if(!hasNetworkEnabled && !hasGpsEnabled)
                throw locationClient.locationException("Gps or Network is not enabled")



            //if they are enabled
            //Make a location request( not deprecated )
            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
                .setMinUpdateIntervalMillis(interval)  // Replaces setFastestInterval()
                .build()

//to use requested location
            //we create call back
            val locationCallBack=object : LocationCallback(){
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    //if location is fetched launch it
                    result.locations.lastOrNull()?.let { location->
                        launch {
                            send(location)  // Assuming `send(location)` is a suspend function
                        }
                    }
                }
            }



         //to get location updates when it gets changed
         //and to connect to flow with callback
         client.requestLocationUpdates(request,locationCallBack, Looper.getMainLooper())
         //to stop collecting data about location
         awaitClose{
             client.removeLocationUpdates(locationCallBack)}

        }
    }
}