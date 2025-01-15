package com.example.weather.common.RecyclerView

import android.view.LayoutInflater
import com.example.weather.R
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.common.Tools.convertToCelesius
import com.example.weather.common.Tools.getImageUrl
import com.example.weather.network.Models.forecastData.Item0
import com.example.weather.network.Models.forecastData.forecastZone
import com.squareup.picasso.Picasso

class ReycyclerAdabter(private val items: List<Item0>): RecyclerView.Adapter<ReycyclerAdabter.ItemViewHolder>() {


    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById(R.id.weather_img)
        val textView: TextView = view.findViewById(R.id.weather_temp)
        val wind: TextView=view.findViewById(R.id.weather_wind)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        var item=items[position]
//        holder.imageView.setImageIcon(item.imageResId)
        val url=getImageUrl(item.weather[0].icon)
        Picasso.get().load(url).into(holder.imageView)
        holder.textView.text="${convertToCelesius(item.main.temp!!)}"
        holder.wind.text="wind\n${item.wind.speed}\n${item.weather[0].description}"
    }

    override fun getItemCount(): Int =items.size


}