package com.vatestkot.my_application_w_nit.data

import com.vatestkot.my_application_w_nit.Weather

interface FavoritesDao {
    /**
     * добавить в избранное
     */
    fun add(weather: Weather)
    /**
     * удалить из избранного
     */
    fun delete(weather: Weather)
    /**
     * @return избранное, м.б. пустым
     */
    fun getAll(): List<Weather>
    /**
     * @return проверка есть ли избранном
     */
    fun isInFavorites(weather: Weather): Boolean
}

