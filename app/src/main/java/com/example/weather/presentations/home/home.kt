package com.example.weather.presentations.home
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.databinding.ActivityHomeBinding
import com.example.weather.R
import com.example.weather.common.Tools.countryPickerDialog
import com.example.weather.presentations.home.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class home : AppCompatActivity() {
    companion object {
       var dat= Bundle()
        fun open(ctx: Context) {
            val intent = Intent(ctx, home::class.java)
            ctx.startActivity(intent)
        }
        fun setData(Dat: DoubleArray)
        {

            dat.putDoubleArray("LatvsLong",Dat)
        }
        fun getData(): DoubleArray? {
            return dat.getDoubleArray("LatvsLong")
        }
    fun setCountry(country: String)
    {
        dat.putString("country",country)
    }
        fun getCountry(): String{
            return dat.getString("country").toString()
        }
       //  var names: MutableList<String> = emptyList<String>() as MutableList<String>

    }
    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityHomeBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.fab).show()
             countryPickerDialog(this)
//            names.add(getCountry())

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.setNavigationItemSelectedListener{ item ->
//            when(item.itemId)
//            {
//R.id.nav_home->{
//
//    true
//}
//                else -> false
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}