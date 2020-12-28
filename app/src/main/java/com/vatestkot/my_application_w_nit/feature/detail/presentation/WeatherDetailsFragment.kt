package com.vatestkot.my_application_w_nit.feature.detail.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vatestkot.my_application_w_nit.R
import com.vatestkot.my_application_w_nit.Weather
import com.vatestkot.my_application_w_nit.data.FavoritesDao
import com.vatestkot.my_application_w_nit.data.FavoritesDaoImpl
import kotlinx.android.synthetic.main.fragment_weather_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.vatestkot.my_application_w_nit.feature.detail.presentation.WeatherDetailPresenter
import com.vatestkot.my_application_w_nit.feature.detail.presentation.WeatherDetailView


class WeatherDetailsFragment : MvpAppCompatFragment(R.layout.fragment_weather_details),
        WeatherDetailView {

    companion object {

        private const val WEATHER = "WEATHER"

        fun newInstance(weather: Weather) =
                WeatherDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(WEATHER, weather)
                    }
                }
    }

    private val presenter: WeatherDetailPresenter by moxyPresenter {
        WeatherDetailPresenter(
            weather = arguments?.getParcelable(WEATHER)!!,
            favoritesDao = FavoritesDaoImpl(
                requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDetailFavorite.setOnClickListener {
            presenter.onFavoritesClicked()
        }
    }

    override fun setWeather(weather: Weather) {
        tvName.text = "Место: ${weather.city}"
        tvTemp.text = "Температура: ${weather.temp}"

    }
    override fun setIsInFavorites(inFavorites: Boolean) {
        weatherDetailFavorite.setImageResource(
            if (inFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
    }

}