package com.allerap.android.mapchallenge

import android.app.Activity
import android.app.Application
import android.app.Service
import com.allerap.android.mapchallenge.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
        initImageManager()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector

    private fun initDependencyInjection() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun initImageManager() {
        Fresco.initialize(this)
    }
}
