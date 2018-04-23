package com.allerap.android.mapchallenge.data

import com.allerap.android.mapchallenge.data.mappers.RateMapper
import com.allerap.android.mapchallenge.data.mappers.StopMapper
import com.allerap.android.mapchallenge.data.requests.EstimateRequest
import com.allerap.android.mapchallenge.data.result.Result
import com.allerap.android.mapchallenge.data.result.map

import com.allerap.android.mapchallenge.domain.entities.Error
import com.allerap.android.mapchallenge.domain.entities.Rate
import com.allerap.android.mapchallenge.domain.entities.Stop
import com.squareup.moshi.Moshi

class ApiRemoteSource(private val service: ApiService,
                      moshi: Moshi,
                      private val stopMapper: StopMapper,
                      private val rateMapper: RateMapper

) : BaseRemoteSource(moshi) {

    fun estimateJourney(stops: List<Stop>, startsAt: String?): Result<List<Rate>, Error> {
        return call(service.estimateRate(
                EstimateRequest(stopMapper.mapModelToEntity(stops), startsAt)))
                .map {
                    rateMapper.mapEntityToModel(it)
        }
    }
}
