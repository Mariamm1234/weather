package com.example.weather.presentations.home.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.weather.common.Tools.countryList
import com.example.weather.databinding.FragmentGalleryBinding
import com.example.weather.home2
import com.example.weather.presentations.home.TabAdabter
import com.example.weather.presentations.home.home
import com.example.weather.presentations.home.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GalleryFragment : Fragment() {
companion object{
    val dat= Bundle()
    fun setCountry(country: String)
    {
        dat.putString("country",country)
    }
    fun getCountry(): String{
        return dat.getString("country").toString()
    }
}
private var _binding: FragmentGalleryBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private lateinit var adapter: TabAdabter
    private var tabCount = 0
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

    _binding = FragmentGalleryBinding.inflate(inflater, container, false)
    val root: View = binding.root
      adapter= TabAdabter(this@GalleryFragment)


binding.viewPager.adapter=adapter


      TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
          tab.text=adapter.getTabTitle(position)
      }.attach()

      Log.i("string",getCountry())
      addNewTab(HomeFragment.getTitle())
      for (i in 0..countryList.size-1)
      {
          addNewTab(countryList.elementAt(i))
      }
//      if(!getCountry().equals("null"))
//      {
//          addNewTab(getCountry())
//          setCountry("null")
//      }
//      if(home.getCountry().isNotEmpty())
//      {
//          val tabTitle = "${getCountry()}"
//          addNewTab(tabTitle)
////          tabCount++
////          val tabTitle = "${getCountry()}"
////          val fragment = HomeFragment.newInstance("${tabTitle}")
////          adapter.addTab(fragment, tabTitle)
////
////          // Automatically switch to the new tab
//          binding.viewPager.currentItem = adapter.itemCount -1
//      }
//      else{}

    return root
  }

    fun addNewTab(title: String) {
        adapter.addTab( home2.newInstance(title),title)
    }

    override fun onStart() {
        super.onStart()

//        for (i in 0..countryList.size-1)
//        {
//            addNewTab(countryList.elementAt(i))
//        }
    }

    override fun onPause() {
        super.onPause()
    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}