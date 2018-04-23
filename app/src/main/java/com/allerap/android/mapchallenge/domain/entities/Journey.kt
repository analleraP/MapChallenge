package com.allerap.android.mapchallenge.domain.entities

import java.io.Serializable

data class Journey(
        val vehicle: Vehicle,
        val priceFormatted: String
) : Serializable
