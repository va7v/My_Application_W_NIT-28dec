package com.vatestkot.my_application_w_nit.data

import android.content.SharedPreferences
import com.vatestkot.my_application_w_nit.Weather
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoritesDaoImpl(
    private val sharedPreferences: SharedPreferences
    ): FavoritesDao {

    private var weathers: List<Weather>
        get() = sharedPreferences.getString(FAVORITES_DAO_KEY, null)?.let{
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()

        set(value) {
            sharedPreferences.edit().putString(
                FAVORITES_DAO_KEY,
                Json.encodeToString(value)
                ).apply()
        }

    override fun add(weather: Weather) {
        weathers = weathers + weather
    }

    override fun delete(weather: Weather) {
        weathers = weathers.filter { it != weather }
    }

    override fun getAll(): List<Weather> = weathers

    override fun isInFavorites(weather: Weather): Boolean = weathers.contains(weather)

    companion object {

        private const val FAVORITES_DAO_KEY = "FAVORITES_DAO_KEY"
    }
}