package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.Eta
import com.allerap.android.mapchallenge.data.entities.Icons
import com.allerap.android.mapchallenge.domain.entities.Vehicle
import com.allerap.android.mapchallenge.data.entities.VehicleTypeEntity

class VehicleMapper : Mapper<VehicleTypeEntity, Vehicle>() {

    override fun mapModelToEntity(model: Vehicle): VehicleTypeEntity {

        return VehicleTypeEntity(
                model.id,
                model.name,
                model.shortName,
                model.description,
                Icons(model.imageUrl),
                Eta(model.minSecondsToArrive, model.maxSecondsToArrive, model.timeEstimation))
    }

    override fun mapEntityToModel(entity: VehicleTypeEntity): Vehicle {

        return Vehicle(
                entity.id,
                entity.name,
                entity.shortName,
                entity.description,
                entity.icons.regular,
                entity.eta?.formatted,
                entity.eta?.min,
                entity.eta?.max)
    }
}
