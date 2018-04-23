package com.allerap.android.mapchallenge.feature.fake

import com.allerap.android.mapchallenge.data.entities.Eta
import com.allerap.android.mapchallenge.data.entities.Icons
import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity

class FakeVehicleTypeEntity {

    fun aVehicleTypeEntity() = VehicleTypeEntity(
            "330997a2df70ac21705634b938313873",
            "Cabify Dev Test",
            "Dev Test",
            "This product is intended for testing purposes",
            Icons("https://test.cabify.com/images/icons/vehicle_type/rickshaw_54.png"),
            Eta())
}