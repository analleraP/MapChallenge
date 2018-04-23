package com.allerap.android.mapchallenge.feature.map

import com.allerap.android.mapchallenge.data.result.onFailure
import com.allerap.android.mapchallenge.data.result.onSuccess
import com.allerap.android.mapchallenge.domain.entities.Stop
import com.google.android.gms.maps.model.LatLng

class MapPresenter(private val view: MapView,
                   private val interactor: MapInteractor,
                   private val navigator: MapNavigator) {

    fun onRateBtnClicked(
            origin: LatLng,
            destination: LatLng) {

        getEstimateJourney(origin, destination)
    }

    private fun getEstimateJourney(
            origin: LatLng,
            destination: LatLng
    ) = interactor.getEstimateJourney(
            Stop(origin.latitude, origin.longitude),
            Stop(destination.latitude, destination.longitude)) {

        it.onSuccess {
            navigator.toJourneyListScreen(it)
        }

        it.onFailure {
            view.handleError(it.message)
        }
    }
}
