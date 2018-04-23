package com.allerap.android.mapchallenge.data.entities

import com.squareup.moshi.Json

data class VehicleTypeEntity(
        @Json(name = "_id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "short_name")
        val shortName: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "icons")
        val icons: Icons,
        val eta: Eta?
)

data class Icons(
        @Json(name = "regular")
        val regular: String
)

data class Eta(
        val min: Int? = null,
        val max: Int? = null,
        val formatted: String? = null
)