package com.allerap.android.mapchallenge.domain

import com.allerap.android.mapchallenge.data.ApiRemoteSource
import com.allerap.android.mapchallenge.data.result.Result
import com.allerap.android.mapchallenge.domain.entities.Error
import com.allerap.android.mapchallenge.domain.entities.Journey
import com.allerap.android.mapchallenge.domain.entities.Stop

class Repository(private val remoteSource: ApiRemoteSource) {

    fun estimateJourney(stops: List<Stop>): Result<List<Journey>, Error> =
            remoteSource.estimateJourney(stops, null)
}
