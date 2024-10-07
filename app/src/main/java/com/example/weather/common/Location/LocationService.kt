package com.example.weather.common.Location

import android.R
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

//create notification to show location data
class LocationService: Service() {
    private val serviceScope= CoroutineScope(SupervisorJob()+ Dispatchers.IO)
    private lateinit var  locationclient: locationClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
//do location part(DefaultLocationClient)
    override fun onCreate() {
        super.onCreate()
        locationclient= DefaultLocationClient(applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action)
        {
            ACTION_START->start()
            ACTION_STOP->stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }
    //to show notification content

  @RequiresApi(Build.VERSION_CODES.Q)
  private fun start()
  {
      //notification setup
      val notification= NotificationCompat.Builder(
          this,"weather"
      )
          .setContentTitle("Tracking Location ....")
          .setContentText("Location:null")
          .setSmallIcon(R.drawable.stat_notify_sync)
          .setOngoing(true)
val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      //to show updating event in notification
      locationclient.getLocationUpdate(10000L)
          .catch { e->e.printStackTrace() }
          .onEach { location->
              val lat=location.latitude.toString().takeLast(3)
              val long=location.longitude.toString().takeLast(3)
              val updateNotification=notification.setContentText("Location:($lat,$long)")
              notificationManager.notify(1,updateNotification.build())
          }
          .launchIn(serviceScope)
      startForeground(1,notification.build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION)
  }
    private fun stop()
    {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
    companion object{
        const val ACTION_START="ACTION_START"
        const val ACTION_STOP="ACTION_STOP"
    }
}