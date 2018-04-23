package com.allerap.android.mapchallenge.feature.map

import com.allerap.android.mapchallenge.data.result.Ok
import com.allerap.android.mapchallenge.domain.Repository
import com.allerap.android.mapchallenge.domain.entities.Journey
import com.allerap.android.mapchallenge.extensions.whenever
import com.allerap.android.mapchallenge.feature.TestCoroutineContextPool
import com.allerap.android.mapchallenge.feature.fake.FakeJourney
import com.allerap.android.mapchallenge.feature.fake.FakeStop
import kotlin.test.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class MapInteractorTest {

    private val contextPool = TestCoroutineContextPool()
    private val repository: Repository = mock(Repository::class.java)
    private val mapInteractor: MapInteractor =
            MapInteractor(contextPool, repository)

    @Test
    fun `gets journeys`() {
        givenThereAreJourneys()

        mapInteractor.getEstimateJourney(FakeStop().aStop(), FakeStop().aStop(), {})

        verify(repository, times(1))
                .estimateJourney(FakeStop().aStopListOfTwo())
    }

    private fun givenThereAreJourneys(): List<Journey> {
        val journeys = FakeJourney().someJourneys()

        whenever(repository.estimateJourney(FakeStop().aStopListOfTwo()))
                .thenReturn(Ok(journeys))

        return journeys
    }
}
