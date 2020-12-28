package com.vatestkot.my_application_w_nit.feature.topWeather.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vatestkot.my_application_w_nit.R
import com.vatestkot.my_application_w_nit.Weather
import com.vatestkot.my_application_w_nit.feature.topWeather.presentation.TopWeathersPresenter
import com.vatestkot.my_application_w_nit.feature.topWeather.presentation.TopWeatherView

import com.vatestkot.my_application_w_nit.feature.detail.presentation.WeatherDetailsFragment
import com.vatestkot.my_application_w_nit.feature.detail.ui.FavoritesFragment
import com.vatestkot.my_application_w_nit.feature.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_top_weather.*
import kotlinx.android.synthetic.main.top_weather_item.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class TopWeathersFragment : MvpAppCompatFragment(R.layout.fragment_top_weather), TopWeatherView {

    private var weathersAdapter : TopWeathersAdapter? = null
    private val presenter: TopWeathersPresenter by moxyPresenter {
        TopWeathersPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        btnGoToFavorities.setOnClickListener {
            presenter.onFavoritesClick()
        }
*/
        with(rvTopWeathers) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TopWeathersAdapter(onWeatherClick = {
                presenter.onWeatherClick(it) }).also {
                weathersAdapter = it

            }
        }
/*
        btnGoToSearch.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, SearchFragment())
                    .addToBackStack("SearchFragment")
                    .commit()
        }*/
    }

    override fun onDestroyView() {
        weathersAdapter = null
        super.onDestroyView()
    }

    /*override fun showWeather(weathers: List<Weather>) {
        weathersAdapter?.setData(weathers)
    }*/

    //
    override fun setWeather(weathers: List<Weather>) {
        weathersAdapter?.setData(weathers)
    }

    //Weather Detail
    override fun openDetailScreen(weather: Weather) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, WeatherDetailsFragment.newInstance(weather))
            .addToBackStack("WeatherDetailsFragment")
            .commit()
    }

    override fun openFavoritesScreen() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, FavoritesFragment())
            .addToBackStack("FavoritesFragment")
            .commit()
    }
}

