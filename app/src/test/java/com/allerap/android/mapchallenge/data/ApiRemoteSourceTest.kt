package com.allerap.android.mapchallenge.data

import com.allerap.android.mapchallenge.domain.entities.Stop
import kotlin.test.BeforeTest
import kotlin.test.Test

class ApiRemoteSourceTest : BaseRemoteSourceTest() {

    private lateinit var service: ApiService
    private lateinit var remoteSource: ApiRemoteSource

    @BeforeTest
    override fun setUp() {
        super.setUp()
        service = create(ApiService::class.java)
        remoteSource = ApiRemoteSource(service, moshi, stopMapper, rateMapper)
    }

    @Test
    fun `sends a POST request to the correct endpoint while estimate the journey rate`() {
        enqueueMockResponse(200, "estimateRateResponse.json")

        val stopsDouble = listOf(Stop(40.41133490000001,-3.6749081),
                Stop(40.44832999999999,-3.69251))

        remoteSource.estimateJourney(stopsDouble, null)

        assertPostRequestSentTo("/api/v2/estimate")
    }

    @Test
    fun `the request body contains all necessary data while estimate the journey rate`() {
        enqueueMockResponse(200, "estimateRateResponse.json")

        val stopsDouble = listOf(Stop(40.41133490000001,-3.6749081),
                Stop(40.44832999999999,-3.69251))

        remoteSource.estimateJourney(stopsDouble, null)

        assertRequestBodyEquals("estimateRateRequest.json")
    }
}
