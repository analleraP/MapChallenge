package com.allerap.android.mapchallenge.feature.map

import android.content.Context
import com.allerap.android.mapchallenge.domain.entities.Rate
import com.allerap.android.mapchallenge.feature.rate.ratesIntent

class MapNavigator(private val context: Context)  {

    fun toJourneyListScreen(rates: List<Rate>) {
        context.startActivity(context.ratesIntent(rates))
    }
}
