package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.RateEntity
import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity
import com.allerap.android.mapchallenge.domain.entities.Rate
import com.allerap.android.mapchallenge.feature.fake.FakeRate
import com.allerap.android.mapchallenge.feature.fake.FakeVehicleTypeEntity
import kotlin.test.Test
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

    private fun givenARateModel(): Rate {

        return FakeRate().aRate()
    }

    private fun givenAVehicleEntity(): VehicleTypeEntity = FakeVehicleTypeEntity().aVehicleTypeEntity()
}
