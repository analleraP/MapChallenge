package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.Eta
import com.allerap.android.mapchallenge.data.entities.Icons
import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity
import com.allerap.android.mapchallenge.domain.entities.Vehicle
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class VehicleMapperTest {

    private lateinit var vehicleMapper: VehicleMapper

    @BeforeTest
    fun setUp() {
        vehicleMapper = VehicleMapper()
    }

    @Test
    fun `gets entity from model`() {

        val expected = givenAVehicleEntity()
        val vehicle = vehicleMapper.mapEntityToModel(expected)

        assertEquals(expected.description, vehicle.description)
        assertEquals(expected.id, vehicle.id)
        assertEquals(expected.name, vehicle.name)
        assertEquals(expected.shortName, vehicle.shortName)
    }

    @Test
    fun `gets model from entity`() {

        val expected = givenAVehicleModel()
        val vehicleTypeEntity = vehicleMapper.mapModelToEntity(expected)

        assertEquals(expected.description, vehicleTypeEntity.description)
        assertEquals(expected.id, vehicleTypeEntity.id)
        assertEquals(expected.name, vehicleTypeEntity.name)
        assertEquals(expected.shortName, vehicleTypeEntity.shortName)
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
