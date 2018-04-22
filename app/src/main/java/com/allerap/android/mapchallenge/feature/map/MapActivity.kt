package com.allerap.android.mapchallenge.feature.map

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allerap.android.mapchallenge.R
import com.allerap.android.mapchallenge.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*
import javax.inject.Inject

class MapActivity : BaseActivity(), MapView {

    @Inject
    lateinit var presenter: MapPresenter

    private lateinit var mapComponent: MapComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        configView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            mapComponent.onActivityResult(requestCode, data)
        }
    }

    private fun configView() {

        mapComponent = MapComponent(this)
        mapComponent.initialize()

        btnRate.setOnClickListener {

            if (mapComponent.originPosition != null && mapComponent.destinationPosition != null) {
                //presenter.onRateBtnClicked(origin, destination)
            } else {
                //sanckbar requesting locations
            }
        }
    }
}
