package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.Eta
import com.allerap.android.mapchallenge.data.entities.Icons
import com.allerap.android.mapchallenge.data.entities.RateEntity
import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity
import com.allerap.android.mapchallenge.domain.entities.Journey
import com.allerap.android.mapchallenge.domain.entities.Vehicle
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class RateMapperTest {

    private lateinit var rateMapper: RateMapper

    @BeforeTest
    fun setUp() {
        rateMapper = RateMapper(VehicleMapper())
    }

    @Test
    fun `gets entity from model`() {

        val expected = givenARateEntity()
        val journey = rateMapper.mapEntityToModel(expected)

        assertEquals(expected.priceFormatted, journey.priceFormatted)
        assertEquals(expected.vehicleType.id, journey.vehicle.id)
        assertEquals(expected.vehicleType.description, journey.vehicle.description)
    }

    @Test
    fun `gets model from entity`() {

        val expected = givenARateModel()
        val rateEntity = rateMapper.mapModelToEntity(expected)

        assertEquals(expected.priceFormatted, rateEntity.priceFormatted)
        assertEquals(expected.vehicle.id, rateEntity.vehicleType.id)
        assertEquals(expected.vehicle.description, rateEntity.vehicleType.description)
    }

    private fun givenARateEntity(): RateEntity {

        return RateEntity(givenAVehicleEntity(),
                "12.34€")
    }

    private fun givenARateModel(): Journey {

        return Journey(givenAVehicleModel(),
                "12.34€")
    }

    private fun givenAVehicleEntity(): VehicleTypeEntity {

        return VehicleTypeEntity(
                "330997a2df70ac21705634b938313873",
                "Cabify Dev Test",
                "Dev Test",
                "This product is intended for testing purposes",
                Icons("https://test.cabify.com/images/icons/vehicle_type/rickshaw_54.png"),
                Eta())
    }

    private fun givenAVehicleModel(): Vehicle {

        return Vehicle(
                "330997a2df70ac21705634b938313873",
                "Cabify Dev Test",
                "Dev Test",
                "This product is intended for testing purposes",
                "https://test.cabify.com/images/icons/vehicle_type/rickshaw_54.png",
                ">2 min",
                100,
                1000
        )
    }
}
