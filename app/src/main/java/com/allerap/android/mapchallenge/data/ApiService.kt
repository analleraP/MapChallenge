package com.allerap.android.mapchallenge.data

import com.allerap.android.mapchallenge.data.entities.RateEntity
import com.allerap.android.mapchallenge.data.requests.EstimateRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v2/estimate")
    fun estimateRate(@Body estimateRequest: EstimateRequest): Call<List<RateEntity>>
}