package com.example.weather

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.common.RecyclerView.ReycyclerAdabter
import com.example.weather.common.Tools.convertToCelesius
import com.example.weather.common.Tools.getFormattedDate
import com.example.weather.common.Tools.getImageUrl
import com.example.weather.common.constants.Resource
import com.example.weather.databinding.FragmentHome2Binding
import com.example.weather.presentations.home.ui.home.HomeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home2.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class home2 : Fragment() {
//    companion object {
//        val LL= Bundle()
//        val args = Bundle()
//        val title= Bundle()
//        fun newInstance(content: String): home2 {
//            val fragment = home2()
//            args.putString("content", content)
//            fragment.arguments = args
//            //code for fragment here
//            return fragment
//        }
//        fun setTitle(name: String)
//        {
//            title.putString("name",name)
//        }
//        fun getTitle():String{
//            return title.getString("name").toString()
//        }
//        fun setLL(Dat: DoubleArray){
//            LL.putDoubleArray("FragmentLatLong",Dat)
//        }
//        fun getLL(): DoubleArray?{
//            return LL.getDoubleArray("FragmentLatLong")
//        }
//    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHome2Binding? = null
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeViewModel: HomeViewModel by viewModels()
        _binding = FragmentHome2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val value =  arguments?.getString(ARG_PARAM1)
        if(!value.equals("param1"))
        {

Log.i("value2",value.toString())
            homeViewModel.getGeoData(
                value.toString(),
                requireContext()
            )
            lifecycleScope.launch {
                homeViewModel.geoState.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                            // Show loading UI
                        }

                        is Resource.Error -> {
                            // Show error message
                            //   Toast.makeText(this@intro, state.message, Toast.LENGTH_LONG).show()
//                                viewModel.getWeatherData(lon = lon, lat = lat, lang = "en",this@intro)
                        }

                        is Resource.Success -> {
                            // Update UI with weather data
                            val countr = state.data
                            setLL(
                                doubleArrayOf(
                                    countr?.get(0)?.lon!!,
                                    countr[0].lat
                                )
                            )
//                        home.setData(
//                            doubleArrayOf(
//                                country?.get(0)?.lon!!,
//                                country[0].lat
//                            )
//                        )




                            //try
                            homeViewModel.getWeatherData(
                                getLL()!![0],
                                getLL()!![1],
                                "en",
                                requireContext()
                            )
//                        homeViewModel.getWeatherData(
//                            home.getData()!![0],
//                            home.getData()!![1],
//                            "en",
//                            requireContext()
//                        )
                            homeViewModel.getFiveDayForecast(
                                getLL()!![0],
                                getLL()!![1],
                                "en",
                                requireContext()
                            )
                            // homeViewModel.getDailyForecast(home.getData()!![0], home.getData()!![1],"en", requireContext())
//                        homeViewModel.getFiveDayForecast(
//                            home.getData()!![0],
//                            home.getData()!![1],
//                            "en",
//                            requireContext()
//                        )
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

                        }

                        // Use `weatherData` in your views
                    }
                }


            }
            Log.i("new fragment",value.toString())
//            arguments?.putString("content","null")




        }


    return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            home2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
        val LL= Bundle()
        fun setLL(Dat: DoubleArray){
            LL.putDoubleArray("FragmentLatLong",Dat)
        }
        fun getLL(): DoubleArray?{
            return LL.getDoubleArray("FragmentLatLong")
        }
    }
}