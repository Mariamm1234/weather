package com.example.weather.presentations.home

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weather.presentations.home.ui.gallery.GalleryFragment

class TabAdabter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    @SuppressLint("NotifyDataSetChanged")
    fun addTab(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
//        notifyItemInserted(fragments.size - 1)
        notifyDataSetChanged()
    }

    fun getTabTitle(position: Int): String = titles[position]
}