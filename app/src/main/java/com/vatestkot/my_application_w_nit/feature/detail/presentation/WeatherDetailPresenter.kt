package com.vatestkot.my_application_w_nit.feature.detail.presentation

import com.vatestkot.my_application_w_nit.Weather
import com.vatestkot.my_application_w_nit.data.FavoritesDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class WeatherDetailPresenter(
    private val weather: Weather,
    private val favoritesDao: FavoritesDao
    ) : MvpPresenter<WeatherDetailView>() {

    private var isInFavorites: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setWeather(weather)
        isInFavorites = favoritesDao.isInFavorites(weather)
        viewState.setIsInFavorites(isInFavorites)
    }

    fun onFavoritesClicked() {
        if (isInFavorites) {
            favoritesDao.delete(weather)
        } else {
            favoritesDao.add(weather)
        }
        isInFavorites = !isInFavorites
        viewState.setIsInFavorites(isInFavorites)
    }
}

interface WeatherDetailView: MvpView{
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWeather(weather: Weather) {
    }

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIsInFavorites(inFavorites: Boolean)
}