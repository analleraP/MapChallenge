package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.ErrorEntity
import com.allerap.android.mapchallenge.domain.entities.Error

class ErrorMapper : Mapper<ErrorEntity, Error>() {

    override fun mapModelToEntity(model: Error): ErrorEntity {

        return ErrorEntity(model.message)
    }

    override fun mapEntityToModel(entity: ErrorEntity): Error {

        return Error(entity.message)
    }
}
