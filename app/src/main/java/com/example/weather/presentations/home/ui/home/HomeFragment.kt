package com.example.weather.presentations.home.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.common.RecyclerView.Item
import com.example.weather.common.RecyclerView.ReycyclerAdabter
import com.example.weather.common.Tools.convertToCelesius
import com.example.weather.common.Tools.getFormattedDate
import com.example.weather.common.Tools.getImageUrl
//import com.example.weather.common.Tools.tabLogic
import com.example.weather.common.constants.Resource
import com.example.weather.common.state
import com.example.weather.databinding.FragmentHomeBinding
//import com.example.weather.presentations.home.TabAdabter
import com.example.weather.presentations.home.home
import com.example.weather.presentations.home.ui.gallery.GalleryFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        val LL= Bundle()
        val args = Bundle()
        val title= Bundle()
        fun newInstance(content: String): HomeFragment {
            val fragment = HomeFragment()
            args.putString("content", content)
            fragment.arguments = args
            //code for fragment here
            return fragment
        }
        fun setTitle(name: String)
        {
            title.putString("name",name)
        }
        fun getTitle():String{
            return title.getString("name").toString()
        }
        fun setLL(Dat: DoubleArray){
            LL.putDoubleArray("FragmentLatLong",Dat)
        }
        fun getLL(): DoubleArray?{
            return LL.getDoubleArray("FragmentLatLong")
        }
    }
private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  @RequiresApi(Build.VERSION_CODES.O)
  @SuppressLint("SuspiciousIndentation")
  private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReycyclerAdabter
//private lateinit var tabAdabter: TabAdabter
  @SuppressLint("SuspiciousIndentation")
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

//    val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
    val homeViewModel: HomeViewModel by viewModels()
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // tabLogic(names = home.names,binding.viewPager,binding.tabLayout,tabAdabter)
//      Log.i("fragment","${home.getData()!![0]}+${ home.getData()!![1]}")
//      if(count>1)
//      {
//          binding.scroll.visibility= View.GONE
//          binding.tabs.visibility= View.VISIBLE
//Log.i("tabs","tabs")
//      }
//      if (count == 1)
//      {



//        val value =  arguments?.getString("content")
//    if(!value.isNullOrEmpty())
//    {
//        homeViewModel.getGeoData(
//            value.toString(),
//            requireContext()
//        )
//        lifecycleScope.launch {
//            homeViewModel.geoState.collect { state ->
//                when (state) {
//                    is Resource.Loading -> {
//                        // Show loading UI
//                    }
//
//                    is Resource.Error -> {
//                        // Show error message
//                        //   Toast.makeText(this@intro, state.message, Toast.LENGTH_LONG).show()
////                                viewModel.getWeatherData(lon = lon, lat = lat, lang = "en",this@intro)
//                    }
//
//                    is Resource.Success -> {
//                        // Update UI with weather data
//                        val country = state.data
//                        setLL(
//                            doubleArrayOf(
//                                country?.get(0)?.lon!!,
//                                country[0].lat
//                            )
//                        )
////                        home.setData(
////                            doubleArrayOf(
////                                country?.get(0)?.lon!!,
////                                country[0].lat
////                            )
////                        )
//
//
//
//
//                        //try
//                        homeViewModel.getWeatherData(
//                            getLL()!![0],
//                            getLL()!![1],
//                            "en",
//                            requireContext()
//                        )
////                        homeViewModel.getWeatherData(
////                            home.getData()!![0],
////                            home.getData()!![1],
////                            "en",
////                            requireContext()
////                        )
//                        homeViewModel.getFiveDayForecast(
//                            getLL()!![0],
//                            getLL()!![1],
//                            "en",
//                            requireContext()
//                        )
//                        // homeViewModel.getDailyForecast(home.getData()!![0], home.getData()!![1],"en", requireContext())
////                        homeViewModel.getFiveDayForecast(
////                            home.getData()!![0],
////                            home.getData()!![1],
////                            "en",
////                            requireContext()
////                        )
//                        lifecycleScope.launch(Dispatchers.Main) {
//                            homeViewModel.state.collect { state ->
//                                when (state) {
//                                    is Resource.Loading -> {
////                    recyclerView=binding.forecast
////                    recyclerView.layoutManager= LinearLayoutManager(requireContext())
//                                        //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
//
//                                    }
//
//                                    is Resource.Error -> {
//                                        Log.i("error", "something wrong")
//                                        // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
//                                    }
//
//                                    is Resource.Success -> {
//                                        Log.i("dataaaa", state.data.toString())
//                                        binding.apply {
////                                           country.text = state.data?.name.toString()
//
//                                            date.text = getFormattedDate()
//                                            degree.text = convertToCelesius(state.data?.main?.temp!!)
//                                            realfeel.text = "${convertToCelesius(state.data?.main?.temp_max!!)}/${
//                                                convertToCelesius(state.data?.main?.temp_min!!)
//                                            }   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
//                                            type.text = state.data?.weather?.get(0)?.main
//                                            val url = getImageUrl(state.data.weather[0].icon)
//                                            Picasso.get().load(url).into(icon)
//                                        }
//
//                                    }
//
//                                }
//                            }
//                            /*
//                              homeViewModel.daily.collect{
//                                  state->
//                                  when(state)
//                                  {
//                                      is  Resource.Loading->
//                                      {
//
//                                          //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
//
//                                      }
//                                      is Resource.Error->
//                                      {
//                                          Log.i("error","something wrong")
//                                          // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
//                                      }
//
//                                      is Resource.Success->{
//                                          recyclerView=binding.forecast
//                                          recyclerView.layoutManager= LinearLayoutManager(requireContext())
//                                          Log.i("dataaaa2",state.data.toString())
//                                          var list= state.data?.list
//                                          var items=listOf<Item>()
//
//
//                                          for (i in 0.. list!!.size)
//                                          {
//                                              var j=list[i]
//                                              var item= Item(j.weather[i].icon, j.main.temp.toString())
//                                              items.plus(item)
//
//                                          }
//                                          adapter= ReycyclerAdabter(items)
//                                          recyclerView.adapter=adapter
//                    //                      binding.apply {
//                    //                          country.text= state.data?.name.toString()
//                    //                          date.text= getFormattedDate()
//                    //                          degree.text= convertToCelesius(state.data?.main?.temp!!)
//                    //                          realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
//                    //                          type.text= state.data?.weather?.get(0)?.main
//                    //                      }
//
//                                      }
//
//                                  }
//                              }*/
//
//                        }
//
//                        binding.tabs.visibility = View.GONE
//                        binding.scroll.visibility = View.VISIBLE
//                        lifecycleScope.launch(Dispatchers.Main) {
//                            homeViewModel.daily.collect { state ->
//                                when (state) {
//                                    is Resource.Loading -> {
//
//                                        //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
//
//                                    }
//
//                                    is Resource.Error -> {
//                                        Log.i("error", "something wrong")
//                                        // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
//                                    }
//
//                                    is Resource.Success -> {
//                                        Log.i("dataaaa2", state.data.toString())
//                                        recyclerView = binding.forecast
//                                        recyclerView.layoutManager = LinearLayoutManager(
//                                            requireContext(),
//                                            LinearLayoutManager.HORIZONTAL,
//                                            false
//                                        )
//                                        var list = state.data?.list
//                                        Log.i("list", list.toString())
////                      var items=listOf<Item>()
////
////
////                      for (i in 0.. list!!.size)
////                      {
////                          var j=list[i]
////                          var item= Item(j.weather[i].icon, j.main.temp.toString())
////                          items.plus(item)
////
////                      }
//                                        adapter = ReycyclerAdabter(list!!)
//                                        recyclerView.adapter = adapter
////                      binding.apply {
////                          country.text= state.data?.name.toString()
////                          date.text= getFormattedDate()
////                          degree.text= convertToCelesius(state.data?.main?.temp!!)
////                          realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
////                          type.text= state.data?.weather?.get(0)?.main
////                      }
//
//
//                                    }
//                                }
//                            }
//                        }
//
//                    }
//
//                    // Use `weatherData` in your views
//                }
//            }
//
//
//        }
//        Log.i("new fragment",value)
//        arguments?.putString("content","null")
//    }
   // else {
        homeViewModel.getWeatherData(
            home.getData()!![0],
            home.getData()!![1],
            "en",
            requireContext()
        )
        // homeViewModel.getDailyForecast(home.getData()!![0], home.getData()!![1],"en", requireContext())
        homeViewModel.getFiveDayForecast(
            home.getData()!![0],
            home.getData()!![1],
            "en",
            requireContext()
        )
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.state.collect { state ->
                when (state) {
                    is Resource.Loading -> {
//                    recyclerView=binding.forecast
//                    recyclerView.layoutManager= LinearLayoutManager(requireContext())
                        //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()

                    }

                    is Resource.Error -> {
                        Log.i("error", "something wrong")
                        // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                    }

                    is Resource.Success -> {
                        Log.i("dataaaa", state.data.toString())
                        binding.apply {
                            country.text = state.data?.name.toString()
                            setTitle(country.text.toString())
                            date.text = getFormattedDate()
                            degree.text = convertToCelesius(state.data?.main?.temp!!)
                            realfeel.text = "${convertToCelesius(state.data?.main?.temp_max!!)}/${
                                convertToCelesius(state.data?.main?.temp_min!!)
                            }   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
                            type.text = state.data?.weather?.get(0)?.main
                            val url = getImageUrl(state.data.weather[0].icon)
                            Picasso.get().load(url).into(icon)
                        }

                    }

                }
            }
            /*
          homeViewModel.daily.collect{
              state->
              when(state)
              {
                  is  Resource.Loading->
                  {

                      //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()

                  }
                  is Resource.Error->
                  {
                      Log.i("error","something wrong")
                      // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                  }

                  is Resource.Success->{
                      recyclerView=binding.forecast
                      recyclerView.layoutManager= LinearLayoutManager(requireContext())
                      Log.i("dataaaa2",state.data.toString())
                      var list= state.data?.list
                      var items=listOf<Item>()


                      for (i in 0.. list!!.size)
                      {
                          var j=list[i]
                          var item= Item(j.weather[i].icon, j.main.temp.toString())
                          items.plus(item)

                      }
                      adapter= ReycyclerAdabter(items)
                      recyclerView.adapter=adapter
//                      binding.apply {
//                          country.text= state.data?.name.toString()
//                          date.text= getFormattedDate()
//                          degree.text= convertToCelesius(state.data?.main?.temp!!)
//                          realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
//                          type.text= state.data?.weather?.get(0)?.main
//                      }

                  }

              }
          }*/

        }
        binding.tabs.visibility = View.GONE
        binding.scroll.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.daily.collect { state ->
                when (state) {
                    is Resource.Loading -> {

                        //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()

                    }

                    is Resource.Error -> {
                        Log.i("error", "something wrong")
                        // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                    }

                    is Resource.Success -> {
                        Log.i("dataaaa2", state.data.toString())
                        recyclerView = binding.forecast
                        recyclerView.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        var list = state.data?.list
                        Log.i("list", list.toString())
//                      var items=listOf<Item>()
//
//
//                      for (i in 0.. list!!.size)
//                      {
//                          var j=list[i]
//                          var item= Item(j.weather[i].icon, j.main.temp.toString())
//                          items.plus(item)
//
//                      }
                        adapter = ReycyclerAdabter(list!!)
                        recyclerView.adapter = adapter
//                      binding.apply {
//                          country.text= state.data?.name.toString()
//                          date.text= getFormattedDate()
//                          degree.text= convertToCelesius(state.data?.main?.temp!!)
//                          realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
//                          type.text= state.data?.weather?.get(0)?.main
//                      }


                    }
                }
            }
        }

        // homeViewModel.getDailyForecast(home.getData()!![0], home.getData()!![1],"en", requireContext())

//    binding.tabs.visibility = View.GONE
//    binding.scroll.visibility = View.VISIBLE
//    lifecycleScope.launch(Dispatchers.Main) {
//        homeViewModel.daily.collect { state ->
//            when (state) {
//                is Resource.Loading -> {
//
//                    //  Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
//
//                }
//
//                is Resource.Error -> {
//                    Log.i("error", "something wrong")
//                    // Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
//                }
//
//                is Resource.Success -> {
//                    Log.i("dataaaa2", state.data.toString())
//                    recyclerView = binding.forecast
//                    recyclerView.layoutManager = LinearLayoutManager(
//                        requireContext(),
//                        LinearLayoutManager.HORIZONTAL,
//                        false
//                    )
//                    var list = state.data?.list
//                    Log.i("list", list.toString())
////                      var items=listOf<Item>()
////
////
////                      for (i in 0.. list!!.size)
////                      {
////                          var j=list[i]
////                          var item= Item(j.weather[i].icon, j.main.temp.toString())
////                          items.plus(item)
////
////                      }
//                    adapter = ReycyclerAdabter(list!!)
//                    recyclerView.adapter = adapter
////                      binding.apply {
////                          country.text= state.data?.name.toString()
////                          date.text= getFormattedDate()
////                          degree.text= convertToCelesius(state.data?.main?.temp!!)
////                          realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
////                          type.text= state.data?.weather?.get(0)?.main
////                      }
//
//
//                }
//            }
//        }
//    }}
   // }

  //}
//    val textView: TextView = binding.textHome
//    homeViewModel.text.observe(viewLifecycleOwner) {
//      textView.text = it
//    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}