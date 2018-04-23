package com.allerap.android.mapchallenge.feature.map

import com.allerap.android.mapchallenge.CoroutineContextPool
import com.allerap.android.mapchallenge.data.result.Result
import com.allerap.android.mapchallenge.domain.Repository
import com.allerap.android.mapchallenge.domain.entities.Error
import com.allerap.android.mapchallenge.domain.entities.Journey
import com.allerap.android.mapchallenge.domain.entities.Stop
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MapInteractor(private val contextPool: CoroutineContextPool,
                    private val repository: Repository) {

    fun getEstimateJourney(origin: Stop,
                           destination: Stop,
                           ui: (Result<List<Journey>, Error>) -> Unit
    ) = launch(contextPool.ui) {

        val result = async(contextPool.bg) {
            repository.estimateJourney(listOf(origin, destination))
        }.await()

        ui(result)
    }
}
