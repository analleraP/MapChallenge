package com.allerap.android.mapchallenge.data.requests

import com.allerap.android.mapchallenge.data.entities.StopEntity
import com.squareup.moshi.Json

data class EstimateRequest(
        val stops: List<StopEntity>,
        @Json(name = "start_at")
        val startAt: String?
)
