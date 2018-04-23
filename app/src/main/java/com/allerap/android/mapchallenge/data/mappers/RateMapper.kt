package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.RateEntity
import com.allerap.android.mapchallenge.domain.entities.Journey

class RateMapper(private val vehicleMapper: VehicleMapper) : Mapper<RateEntity, Journey>() {

    override fun mapModelToEntity(model: Journey): RateEntity {

        return RateEntity(vehicleMapper.mapModelToEntity(model.vehicle),
                model.priceFormatted)
    }

    override fun mapEntityToModel(entity: RateEntity): Journey {

        return Journey(vehicleMapper.mapEntityToModel(entity.vehicleType),
                entity.priceFormatted)
    }
}
