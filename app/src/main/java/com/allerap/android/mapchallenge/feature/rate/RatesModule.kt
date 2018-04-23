package com.allerap.android.mapchallenge.feature.rate

import android.app.Activity
import com.allerap.android.mapchallenge.domain.entities.Rate
import dagger.Module
import dagger.Provides

@Module
class RatesModule {

    @Provides
    fun provideActivity(activity: RatesActivity): Activity = activity

    @Provides
    fun provideView(activity: RatesActivity): RatesView = activity

    @Provides
    fun providePresenter(view: RatesView): RatesPresenter = RatesPresenter(view)

    @Provides
    fun provideAdapter(activity: RatesActivity): RatesAdapter {

        if (activity.intent.hasExtra(EXTRA_RATES)) {
            val journeys = activity.intent.getSerializableExtra(EXTRA_RATES) as List<Rate>
            return RatesAdapter(journeys)
        } else {
            throw Throwable("Journeys can't be null!")
        }

    }
}
