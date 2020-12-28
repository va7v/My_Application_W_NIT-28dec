package com.vatestkot.my_application_w_nit.feature.search

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface SearchView : MvpView {

    fun showPeriodError()

    fun showLatitudeError()

    fun showLongitudeError()
}