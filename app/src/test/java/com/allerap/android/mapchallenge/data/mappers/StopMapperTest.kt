package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.StopEntity
import com.allerap.android.mapchallenge.domain.entities.Stop
import com.allerap.android.mapchallenge.feature.fake.FakeStop
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class StopMapperTest {

    private lateinit var stopMapper: StopMapper

    @BeforeTest
    fun setUp() {
        stopMapper = StopMapper()
    }

    @Test
    fun `gets entity from model`() {

        val expected = givenAStopEntity()
        val stop = stopMapper.mapEntityToModel(expected)

        assertEquals(expected.loc, listOf(stop.latitude, stop.longitude))
    }

    @Test
    fun `gets model from entity`() {

        val expected = givenAStopModel()
        val stopEntity = stopMapper.mapModelToEntity(expected)

        assertEquals(expected.latitude, stopEntity.loc[0])
        assertEquals(expected.longitude, stopEntity.loc[1])
    }

    private fun givenAStopEntity(): StopEntity {

        return StopEntity(listOf(40.41133490000001, -3.6749081))
    }

    private fun givenAStopModel(): Stop {

        return FakeStop().aStop()
    }
}
