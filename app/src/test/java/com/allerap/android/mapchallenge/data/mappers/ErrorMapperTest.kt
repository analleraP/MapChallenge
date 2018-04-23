package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.ErrorEntity
import com.allerap.android.mapchallenge.domain.entities.Error
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class ErrorMapperTest {

    private lateinit var errorMapper: ErrorMapper

    @BeforeTest
    fun setUp() {
        errorMapper = ErrorMapper()
    }

    @Test
    fun `gets entity from model`() {

        val expected = givenAnErrorEntity()
        val error = errorMapper.mapEntityToModel(expected)

        assertEquals(expected.message, error.message)
    }

    @Test
    fun `gets model from entity`() {

        val expected = givenAnErrorModel()
        val errorEntity = errorMapper.mapModelToEntity(expected)

        assertEquals(expected.message, errorEntity.message)
    }

    private fun givenAnErrorEntity(): ErrorEntity {

        return ErrorEntity("invalid param")
    }

    private fun givenAnErrorModel(): Error {

        return Error("invalid param")
    }
}
