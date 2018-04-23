package com.allerap.android.mapchallenge.di

import com.allerap.android.mapchallenge.feature.rate.RatesActivity
import com.allerap.android.mapchallenge.feature.rate.RatesModule
import com.allerap.android.mapchallenge.feature.map.MapActivity
import com.allerap.android.mapchallenge.feature.map.MapModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all global sub-components within the app.
 */
@Module
abstract class AppBindingModule {

    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun bindMapActivity(): MapActivity

    @ContributesAndroidInjector(modules = [RatesModule::class])
    abstract fun bindRatesActivity(): RatesActivity

    // Add bindings for other sub-components here
}
