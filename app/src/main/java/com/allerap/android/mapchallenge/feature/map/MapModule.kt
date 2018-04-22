package com.allerap.android.mapchallenge.feature.map

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    fun provideActivity(activity: MapActivity): Activity = activity

    @Provides
    fun provideView(activity: MapActivity): MapView = activity

    @Provides
    fun provideInteractor() = MapInteractor()

    @Provides
    fun provideNavigator(activity: Activity): MapNavigator = MapNavigator(activity)

    @Provides
    fun providePresenter(
            view: MapView,
            interactor: MapInteractor,
            navigator: MapNavigator
    ): MapPresenter = MapPresenter(view, interactor, navigator)
}
