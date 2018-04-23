package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity
import com.allerap.android.mapchallenge.domain.entities.Vehicle
import com.allerap.android.mapchallenge.feature.fake.FakeVehicle
import com.allerap.android.mapchallenge.feature.fake.FakeVehicleTypeEntity
import kotlin.test.Test
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

    private fun givenAVehicleEntity(): VehicleTypeEntity = FakeVehicleTypeEntity().aVehicleTypeEntity()

    private fun givenAVehicleModel(): Vehicle = FakeVehicle().aVehicle()
}
