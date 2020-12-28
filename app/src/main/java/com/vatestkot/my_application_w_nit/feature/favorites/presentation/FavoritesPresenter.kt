package com.vatestkot.my_application_w_nit.feature.favorites.presentation

import com.vatestkot.my_application_w_nit.Weather
import com.vatestkot.my_application_w_nit.data.FavoritesDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType



class FavoritesPresenter(
    private val favoritesDao: FavoritesDao
    ) : MvpPresenter<FavoritesView>() {

    private var weathers: List<Weather> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        weathers = favoritesDao.getAll()
        viewState.setWeather(weathers)
    }
}
interface FavoritesView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWeather(weathers: List<Weather>)

}