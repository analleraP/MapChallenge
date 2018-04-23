package com.allerap.android.mapchallenge.data.entities

import com.squareup.moshi.Json

data class RateEntity(
        @Json(name = "vehicle_type")
        val vehicleType: VehicleTypeEntity,
        @Json(name = "price_formatted")
        val priceFormatted: String
)