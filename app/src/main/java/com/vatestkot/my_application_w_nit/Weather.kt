package com.vatestkot.my_application_w_nit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Weather (val city: String, val temp: String) : Parcelable
