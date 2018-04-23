package com.allerap.android.mapchallenge.di

import com.allerap.android.mapchallenge.data.ApiRemoteSource
import com.allerap.android.mapchallenge.data.ApiService
import com.allerap.android.mapchallenge.data.mappers.RateMapper
import com.allerap.android.mapchallenge.data.mappers.StopMapper
import com.allerap.android.mapchallenge.data.mappers.VehicleMapper
import com.allerap.android.mapchallenge.domain.Repository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BusinessAppModule {

    @Provides
    @Singleton
    fun provideStopMapper(): StopMapper = StopMapper()

    @Provides
    @Singleton
    fun provideVehicleMapper(): VehicleMapper = VehicleMapper()

    @Provides
    @Singleton
    fun provideRateMapper(vehicleMapper: VehicleMapper
    ): RateMapper = RateMapper(vehicleMapper)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteSource(
            apiService: ApiService,
            moshi: Moshi,
            stopMapper: StopMapper,
            rateMapper: RateMapper
    ): ApiRemoteSource = ApiRemoteSource(apiService, moshi, stopMapper, rateMapper)

    @Provides
    @Singleton
    fun provideRepository(
            remoteSource: ApiRemoteSource
    ): Repository = Repository(remoteSource)
}
