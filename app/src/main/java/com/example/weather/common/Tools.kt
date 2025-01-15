package com.example.weather.common

import com.example.weather.R
import android.app.Activity
import android.app.Dialog
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
import android.app.ProgressDialog
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.example.weather.databinding.DialogBinding
import com.example.weather.presentations.home.TabAdabter
//import com.example.weather.presentations.home.TabAdabter
import com.example.weather.presentations.home.home
import com.example.weather.presentations.home.ui.gallery.GalleryFragment
import com.example.weather.presentations.home.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hbb20.CountryCodePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object Tools {

var progressDialog:ProgressDialog?=null
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




    }

    fun Start(ctx: Context)
    {
        if(progressDialog!=null)
            return
        try {
            progressDialog= ProgressDialog(ctx)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setMessage("please wait")
            progressDialog!!.show()
        }
        catch (e: Exception) {
        }
    }

    fun End(){
        try {
            if (progressDialog!=null)
            {
                progressDialog!!.dismiss()
                progressDialog=null
            }
        }catch (e: Exception) {
        }
    }


lateinit var alert: AlertDialog
        fun createGeneralDialog(
            ctx:Context,
            titleMessage:String,
            detailMesaage:String,
            tryAgain:String,
            cancel: String,
            action:()->Unit        ){
            //dialog obj with custom background
            val alertObj= AlertDialog.Builder(ctx)
            //display the dialog as view obj can display on screen
            val inflater= (ctx as Activity).layoutInflater
            //binding to access dialog components
            val binding: DialogBinding= DialogBinding.inflate(inflater)

            binding.apply {
                titleTxt.text=titleMessage
                detailTxt.text=detailMesaage
                tryAgainBtn.text=tryAgain
                cancelBtn.text=cancel
            }

            alert=
                alertObj.apply {
                setCancelable(true)
                setView(binding.root)
            }
                .create()

            binding.tryAgainBtn.setOnClickListener{
                action()
                alert.dismiss()
            }
            binding.cancelBtn.setOnClickListener{
                alert.dismiss()
            }
            alert.show()
        }
    /*
    *
    *
    * "${Calendar.getInstance().get(Calendar.DAY_OF_WEEK)},${Calendar.getInstance().get(Calendar.MONTH)} ${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)} ${Calendar.getInstance().get(Calendar.HOUR)}"
    * */
    fun getFormattedDate(): String {
        val date = Date() // Current date and time
        val dateFormat = SimpleDateFormat("EEE, MMMM dd h:mm a", Locale.ENGLISH)
        return dateFormat.format(date)
    }
    fun convertToCelesius(temp:Double): String{
return (temp.minus(273.15).toString().split('.')[0])
    }

    fun getImageUrl(imgId: String): String{
        return "https://openweathermap.org/img/wn/${imgId}@2x.png"
    }
fun countryPickerDialog(ctx: Context):String
{
    val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_country_picker, null)
    val countryPicker=dialogView.findViewById<CountryCodePicker>(R.id.country_picker)
var country=""

    val dialog= AlertDialog.Builder(ctx)
        .setTitle("Select a Country")
        .setView(dialogView)
        .setNegativeButton("Cancel", null)
        .create()

    countryPicker.setOnCountryChangeListener{
        country=countryPicker.selectedCountryEnglishName
        home.setCountry(country)
        Log.i("country",country)
        GalleryFragment.setCountry(country)
        countryList.add(country)
//        home.names.add(country)
        dialog.dismiss()
    }

dialog.show()
//    GalleryFragment.addNewTab()
   // TabAdabter(GalleryFragment()).addTab(HomeFragment.newInstance(country),country)

return country

}
    var countryList= HashSet<String>()
//fun tabLogic(names: List<String>,viewPager: ViewPager2,
//             tabLayout: TabLayout,
//             adapter: TabAdabter
//             )
//{
//    viewPager.adapter=adapter
//    TabLayoutMediator(tabLayout,viewPager){tab,position->
//        tab.text=names[position]
//    }.attach()
//}

}