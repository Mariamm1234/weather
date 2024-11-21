package com.example.weather.presentations.home.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.weather.common.constants.Resource
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.presentations.home.home
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment : Fragment() {


private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

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
      homeViewModel.getWeatherData(home.getData()!!.get(0), home.getData()!!.get(1),"en",requireContext())
      lifecycleScope.launch{
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
                      binding.textHome.text= state.data?.timezone.toString()
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