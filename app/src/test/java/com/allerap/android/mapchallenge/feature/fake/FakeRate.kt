package com.allerap.android.mapchallenge.feature.fake

import com.allerap.android.mapchallenge.domain.entities.Rate

class FakeRate {

    fun aRate() = Rate(FakeVehicle().aVehicle(),
            "12.34€")

    fun someRates() = listOf(aRate(), aRate(), aRate())

}