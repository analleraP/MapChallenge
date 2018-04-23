package com.allerap.android.mapchallenge.feature.fake

import com.allerap.android.mapchallenge.domain.entities.Journey

class FakeJourney {

    fun aJourney() = Journey(FakeVehicle().aVehicle(),
            "12.34€")

    fun someJourneys() = listOf(aJourney(), aJourney(), aJourney())

}