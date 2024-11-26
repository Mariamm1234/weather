package com.example.weather.presentations.home.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.weather.common.Tools.convertToCelesius
import com.example.weather.common.Tools.getFormattedDate
import com.example.weather.common.constants.Resource
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.presentations.home.home
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


private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  @RequiresApi(Build.VERSION_CODES.O)
  @SuppressLint("SuspiciousIndentation")
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

//    val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
val homeViewModel : HomeViewModel by viewModels()
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root
//      Log.i("fragment","${home.getData()!![0]}+${ home.getData()!![1]}")
      homeViewModel.getWeatherData(home.getData()!![0], home.getData()!![1],"en", requireContext())
      lifecycleScope.launch(Dispatchers.Main){
          homeViewModel.state.collect{
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
Log.i("dataaaa",state.data.toString())
                  binding.apply {
                      country.text= state.data?.name.toString()
                      date.text= getFormattedDate()
                      degree.text= convertToCelesius(state.data?.main?.temp!!)
                      realfeel.text="${convertToCelesius(state.data?.main?.temp_max!!)}/${convertToCelesius(state.data?.main?.temp_min!!)}   |   RealFeal ${convertToCelesius(state.data?.main?.feels_like!!)}"
                      type.text= state.data?.weather?.get(0)?.main
                  }
                  }

              }
          }

      }

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