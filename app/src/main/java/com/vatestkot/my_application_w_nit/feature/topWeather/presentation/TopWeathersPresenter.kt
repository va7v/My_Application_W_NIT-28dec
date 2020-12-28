package com.vatestkot.my_application_w_nit.feature.topWeather.presentation

import com.vatestkot.my_application_w_nit.Weather
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.AddToEndStrategy

class TopWeathersPresenter : MvpPresenter<TopWeatherView>() {

    private val weathers : List<Weather> = listOf(
        Weather("Челябинск", "-7"),
        Weather("Миасс", "-8"),
        Weather("Сочи", "-1"),
        Weather("Уфа", "-6"),
        Weather("Москва", "-5")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setWeather(weathers)
    }

    fun onWeatherClick(weather: Weather) {
        viewState.openDetailScreen(weather)
    }

    fun onFavoritesClick() {
        viewState.openFavoritesScreen()
    }
}

interface TopWeatherView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWeather(weather: List<Weather>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen(weather: Weather)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFavoritesScreen()
}
