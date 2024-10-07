package com.example.weather.common

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit


object Tools {
//val LOCATION_CODE=1001
//var location: Location?=null
//    var lat:Double=0.0
//    fun isLocationPermissionGranted(ctx: Context): Boolean
//    {
//        return if(ActivityCompat.checkSelfPermission(
//            ctx,
//            android.Manifest.permission.ACCESS_COARSE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//            )!= PackageManager.PERMISSION_GRANTED
//        ){
//            ActivityCompat.requestPermissions(
//                ctx as Activity,
//                arrayOf(
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION
//                ),
//                LOCATION_CODE
//            )
//            false
//        }
//        else
//            true
//
//    }
//
//
//
//
//fun handleLocationPermissionGranted(ctx: Context)
//{
//
//    //to recieve location updates
//    var fusedLocationProviderClient:FusedLocationProviderClient
//    //to request location for updates
//    var locationRequest: LocationRequest
//
//    //locationCallBack is called when fused has new location
//    var locationCallBack: LocationCallback
//    //store current location info
//    var currentLocation: Location?=null
//    var latitude: Double=0.0
//    var longitude: Double=0.0
//    //intialize fusedclient
//    fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(ctx)
//
//    //intialize locationRequest
//    @Suppress("DEPRECATION")
//    locationRequest= LocationRequest().apply {
//        //interval to update each time
//        interval= TimeUnit.SECONDS.toMillis(60)
//
//        //fastest rate
//        fastestInterval= TimeUnit.SECONDS.toMillis(30)
//
//        //maxe time to get location changes
//        maxWaitTime= TimeUnit.MINUTES.toMillis(2)
//
//        priority= LocationRequest.PRIORITY_HIGH_ACCURACY
//    }
//
////intialize call back to get current location and if updates happened
//    locationCallBack=object : LocationCallback(){
//        override fun onLocationResult(locationResult: LocationResult) {
//            super.onLocationResult(locationResult)
//            locationResult.lastLocation?.let {
//                locationByGps->
//                currentLocation=locationByGps
//
//                latitude=locationByGps.latitude
//               location=currentLocation
//                longitude=locationByGps.longitude
//                //use them as i need
//            }?: run{
//                Toast.makeText(ctx,"Location information not available", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Request location updates
//    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, Looper.getMainLooper())
//
//// Optional: Get the last known location (as fallback)
//    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//        if (location != null) {
//            currentLocation = location
//            latitude = location.latitude
//            longitude = location.longitude
//        } else {
//            Toast.makeText(ctx, "Last known location not available", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//}
//
//    fun handleLocationPermissionDenied(ctx: Context)
//    {
//        Toast.makeText(ctx,"Location is denied", Toast.LENGTH_SHORT).show()
//    }



    fun isInternetConnected(ctx: Context): Boolean
    {
        //get connection
        val connectivityManager=ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            //if network works
            val network=connectivityManager.activeNetwork ?:return false
           //type of active network
            val activeNetwork=connectivityManager.getNetworkCapabilities(network)?: return false
            return when{
                //allow wi-fi and cellular only
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                else -> false
            }
        }
        else
        {
            @Suppress("DEPRECATION")
            val networkInfo=connectivityManager.activeNetworkInfo?: return false

            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }




//        //datermine type of needed internet connection
//        val networkRequest= NetworkRequest.Builder().apply {
//            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//            build()}
//        val networkCallBack= object : ConnectivityManager.NetworkCallback(){
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//
//            }
//
//            override fun onCapabilitiesChanged(
//                network: Network,
//                networkCapabilities: NetworkCapabilities
//            ) {
//                super.onCapabilitiesChanged(network, networkCapabilities)
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//            }
//        }
    }

//    fun spinnerPreparation(ctx: Context,list: List<String>,spinner: Spinner): String
//    { var country =""
//        val arrayAdapter= ArrayAdapter(ctx,android.R.layout.simple_spinner_item,list)
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter=arrayAdapter
//        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                country= parent?.getItemAtPosition(position).toString()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//
//            }
//
//        }
//        return country
//    }
}