package com.vatestkot.my_application_w_nit.feature.topWeather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vatestkot.my_application_w_nit.R
import com.vatestkot.my_application_w_nit.Weather
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.top_weather_item.*

class TopWeathersAdapter(private val onWeatherClick : (Weather) -> Unit) :
        RecyclerView.Adapter<TopWeathersAdapter.ViewHolder>() {

    private var weathers: MutableList<Weather> = mutableListOf()

    fun setData(weathers: List<Weather>) {
        this.weathers.clear()
        this.weathers.addAll(weathers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_weather_item, parent, false)
        )
    }
 
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = weathers[position]
        holder.topWeatherName.text  = item.city
        holder.topWeatherTemp.text = item.temp
        holder.containerView.setOnClickListener {
            onWeatherClick(item)
        }
    }

    override fun getItemCount(): Int = weathers.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

}