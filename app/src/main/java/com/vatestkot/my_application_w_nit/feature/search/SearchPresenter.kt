package com.vatestkot.my_application_w_nit.feature.search

import moxy.MvpPresenter
import java.lang.Exception

class SearchPresenter: MvpPresenter<SearchView>() {
    private var selectedType: Type = Type.DAY
    fun setType(type: Type) {
        selectedType = type
    }
    private var Period: Int = 0
    fun setPeriod(days: Int) {
        Period = days
    }
    private var Latitude: Int = 0
    fun setLatitude(latit: Int) {
        Latitude = latit
    }

    fun validate(period:String, latitude: String, longitude: String) {
        when {
            !PeriodIsCorrect(period) -> viewState.showPeriodError()
            !LatitudeIsCorrect(latitude) -> viewState.showLatitudeError()
            !LongitudeIsCorrect(longitude) -> viewState.showLongitudeError()
        }
    }

    fun PeriodIsCorrect(periodText: String): Boolean {
        if (periodText.isEmpty()) return false

        return try {
            val period = periodText.toInt()
            period in 1..5
        } catch (e: Exception) {
            false
        }
    }

    fun LatitudeIsCorrect(latitudeText: String): Boolean {
        if (latitudeText.isEmpty()) return false

        return try {
            val latitude = latitudeText.toFloat() // для проверки крайних координат
            (latitude >= -90) && (latitude <=90)
        } catch (e: Exception) {
            false
        }
    }

    fun LongitudeIsCorrect(longitudeText: String): Boolean {
        if (longitudeText.isEmpty()) return false

        return try {
            val longitude = longitudeText.toInt()
            longitude in -180..180
        } catch (e: Exception) {
            false
        }
    }
}
