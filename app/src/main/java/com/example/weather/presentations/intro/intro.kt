package com.example.weather.presentations.intro

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.models.SlideModel

import com.example.weather.R
import com.example.weather.common.Location.LocationService
import com.example.weather.databinding.ActivityIntroBinding

class intro : AppCompatActivity() {
    companion object {
        fun open(ctx: Context) {
            val intent = Intent(ctx, intro::class.java)
            ctx.startActivity(intent)
        }
    }
    lateinit var binding: ActivityIntroBinding
    private val locationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val latitude = intent?.getDoubleExtra("latitude", 0.0)
            val longitude = intent?.getDoubleExtra("longitude", 0.0)

            // Now you can use the latitude and longitude values in your activity
            binding.country.hint="lat:$latitude \n long:$longitude"
        }
    }



    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),0
        )
        //enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

           Intent(applicationContext, LocationService::class.java)
               .apply {
                   action= LocationService.ACTION_START
                   startForegroundService(this)}
        // Register the BroadcastReceiver
        val intentFilter = IntentFilter("LOCATION_UPDATE")
        registerReceiver(locationReceiver, intentFilter, RECEIVER_NOT_EXPORTED)

//        Toast.makeText(this,"location :($latitude,$longitude)",Toast.LENGTH_LONG).show()
//        if(flag)
//        {
//       binding.country.hint="lat:$latitude \n long:$longitude"}


//        isLocationPermissionGranted(this)


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }


//    fun showSlider()
//    {
//        val imageSlider=binding.slider
//        imageSlider.setImageList(imageList())
//    }
//    fun imageList(): ArrayList<SlideModel>
//    {
//        val imgList= ArrayList<SlideModel>()
//        imgList.apply { add(SlideModel("https://kottke.org/plus/misc/images/ian-fisher-clouds-02.jpg",""))
//            add(SlideModel("https://th.bing.com/th/id/R.9d7e699d0c46de973d6478b315d5b64a?rik=nrYEt1TnIZgaow&pid=ImgRaw&r=0",""))
//            add(SlideModel("https://th.bing.com/th/id/R.1c0861f3cb7c133345601bd97f809fe0?rik=ieqDQSFI9P5uEg&riu=http%3a%2f%2fclipartmag.com%2fimages%2fanimated-pictures-of-the-sun-50.jpg&ehk=jolo75XWEm%2bklg6gVF54s649u%2fK11xNALjbOTMlURUk%3d&risl=&pid=ImgRaw&r=0",""))
//        }
//        return imgList
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if(requestCode==LOCATION_CODE)
//    {
//        if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
//        { handleLocationPermissionGranted(this)
//            Toast.makeText(this,"$location", Toast.LENGTH_LONG).show()
//        }
//        else
//            handleLocationPermissionDenied(this)
//    }





}
