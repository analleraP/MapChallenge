package com.allerap.android.mapchallenge.feature.map

interface MapView {

    fun showLoading()

    fun hideLoading()

    fun handleError(message: String)
}
