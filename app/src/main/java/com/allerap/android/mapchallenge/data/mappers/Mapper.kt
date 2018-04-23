package com.allerap.android.mapchallenge.data.mappers

import java.util.ArrayList

abstract class Mapper<E, M> {
    abstract fun mapModelToEntity(model: M): E

    abstract fun mapEntityToModel(entity: E): M

    fun mapModelToEntity(models: List<M>): List<E> {
        val list = ArrayList<E>()
        for (model in models) {
            list.add(mapModelToEntity(model))
        }
        return list
    }

    fun mapEntityToModel(entities: List<E>): List<M> {
        val list = ArrayList<M>()
        for (entity in entities) {
            list.add(mapEntityToModel(entity))
        }
        return list
    }
}
