package com.allerap.android.mapchallenge.domain.entities

import java.io.Serializable

data class Vehicle (
    val id: String,
    val name: String,
    val shortName: String,
    val description: String,
    val imageUrl: String,
    val timeEstimation: String?,
    val minSecondsToArrive: Int?,
    val maxSecondsToArrive: Int?
) : Serializable
