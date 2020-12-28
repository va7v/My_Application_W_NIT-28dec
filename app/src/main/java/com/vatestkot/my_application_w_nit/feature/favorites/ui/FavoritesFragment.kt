package com.vatestkot.my_application_w_nit.feature.favorites.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vatestkot.my_application_w_nit.R
import com.vatestkot.my_application_w_nit.Weather
import com.vatestkot.my_application_w_nit.data.FavoritesDaoImpl
import com.vatestkot.my_application_w_nit.feature.favorites.presentation.FavoritesPresenter
import com.vatestkot.my_application_w_nit.feature.favorites.presentation.FavoritesView
import com.vatestkot.my_application_w_nit.feature.topWeather.ui.TopWeathersAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FavoritesFragment : MvpAppCompatFragment(R.layout.favorites_fragment), FavoritesView {

    companion object{
        fun newInstance() = FavoritesFragment()
    }

    private val presenter:FavoritesPresenter by moxyPresenter {
        FavoritesPresenter(
            favoritesDao = FavoritesDaoImpl(
                requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            )
        )
    }

    private var favoritesAdapter: TopWeathersAdapter?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FavoritesList) {
            favoritesAdapter = TopWeathersAdapter(onWeatherClick = {
            })
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }
    }

    override fun setWeather(weathers: List<Weather>) {
        favoritesAdapter?.setData(weathers)
    }

}