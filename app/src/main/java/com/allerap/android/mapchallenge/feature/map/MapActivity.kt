package com.allerap.android.mapchallenge.feature.map

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.allerap.android.mapchallenge.R
import com.allerap.android.mapchallenge.extensions.hideLoading
import com.allerap.android.mapchallenge.extensions.showLoading
import com.allerap.android.mapchallenge.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_map.view.*
import javax.inject.Inject

class MapActivity : BaseActivity(), MapView, MapComponent.OnMapActionListener {

    @Inject
    lateinit var presenter: MapPresenter

    private lateinit var mapComponent: MapComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        configView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        layout.hideLoading()

        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            mapComponent.onActivityResult(requestCode, data)
        }
    }

    // OnMapActionListener
    override fun setOrigin(address: String) {
        tvFrom.text = address
    }

    override fun setDestination(address: String) {
        tvTo.text = address
    }

    // MapView
    override fun showLoading() {
        layout.showLoading(layoutInflater)
    }

    override fun hideLoading() {
       hideLoading()
    }

    override fun handleError(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun configView() {

        mapComponent = MapComponent(this, this)
        mapComponent.initialize()

        tvFrom.setOnClickListener {
            layout.showLoading(layoutInflater)
            mapComponent.loadPlacePicker(true)
        }

        tvTo.setOnClickListener {
            layout.showLoading(layoutInflater)
            mapComponent.loadPlacePicker(false)
        }

        btnRate.setOnClickListener {

            if (mapComponent.originPosition != null && mapComponent.destinationPosition != null) {
                val origin = mapComponent.originPosition!!
                val destination = mapComponent.destinationPosition!!
                presenter.onRateBtnClicked(origin, destination)
            } else {
                handleError(getString(R.string.map_error_missing_fields))
            }
        }
    }
}
