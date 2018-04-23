package com.allerap.android.mapchallenge.feature.fake

import com.allerap.android.mapchallenge.domain.entities.Stop

class FakeStop {

    fun aStop() = Stop(40.41133490000001, -3.6749081)

    fun aStopListOfTwo() = listOf(aStop(), aStop())
}