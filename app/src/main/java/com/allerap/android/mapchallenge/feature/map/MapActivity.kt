package com.allerap.android.mapchallenge.feature.map

import android.os.Bundle
import com.allerap.android.mapchallenge.R
import com.allerap.android.mapchallenge.feature.base.BaseActivity
import javax.inject.Inject

class MapActivity : BaseActivity(), MapView {

    @Inject
    lateinit var presenter: MapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

    }
}
