package com.allerap.android.mapchallenge.data.mappers

import com.allerap.android.mapchallenge.data.entities.StopEntity
import com.allerap.android.mapchallenge.domain.entities.Stop

import java.util.ArrayList

class StopMapper : Mapper<StopEntity, Stop>() {

    override fun mapModelToEntity(model: Stop): StopEntity {

        return StopEntity(
                listOf(model.latitude, model.longitude))
    }

    override fun mapEntityToModel(entity: StopEntity): Stop {

        return Stop(
                entity.loc[0],
                entity.loc[1])
    }
}
