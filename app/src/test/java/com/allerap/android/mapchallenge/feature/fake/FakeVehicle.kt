package com.allerap.android.mapchallenge.feature.fake

import com.allerap.android.mapchallenge.domain.entities.Vehicle

class FakeVehicle {

    fun aVehicle() = Vehicle("330997a2df70ac21705634b938313873",
            "Cabify Dev Test",
            "Dev Test",
            "This product is intended for testing purposes",
            "https://test.cabify.com/images/icons/vehicle_type/rickshaw_54.png",
            ">2 min",
            100,
            1000)
}