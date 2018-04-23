package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.RateEntity
import com.allerap.android.mapchallenge.domain.entities.Rate

class RateMapper(private val vehicleMapper: VehicleMapper) : Mapper<RateEntity, Rate>() {

    override fun mapModelToEntity(model: Rate): RateEntity {

        return RateEntity(vehicleMapper.mapModelToEntity(model.vehicle),
                model.priceFormatted)
    }

    override fun mapEntityToModel(entity: RateEntity): Rate {

        return Rate(vehicleMapper.mapEntityToModel(entity.vehicleType),
                entity.priceFormatted)
    }
}
