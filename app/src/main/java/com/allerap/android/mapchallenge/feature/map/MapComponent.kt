package com.allerap.android.mapchallenge.feature.map

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.allerap.android.mapchallenge.R
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_map.view.*
import java.io.IOException


class MapComponent(
        private val appCompatActivity: AppCompatActivity,
        private val onMapActionListener: OnMapActionListener
) : OnMapReadyCallback {

    companion object {

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PLACE_PICKER_REQUEST_FROM = 2
        private const val PLACE_PICKER_REQUEST_TO = 3

        private const val CAMERA_CENTER_PADDING = 100
    }

    interface OnMapActionListener {
        fun setOrigin(address: String)
        fun setDestination(address: String)
    }

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location

    private lateinit var layout: ViewGroup

    private var markers = mutableMapOf<String, Marker>()

    private var originMarkerId = ""
    private var destinationMarkerId = ""

    var originPosition: LatLng? = null
        get() = markers[originMarkerId]?.position

    var destinationPosition: LatLng? = null
        get() = markers[destinationMarkerId]?.position


    fun initialize() {
        layout = appCompatActivity.mapView
        val view = LayoutInflater.from(appCompatActivity)
                .inflate(R.layout.view_map, layout, false)

        layout.addView(view)
        val mapFragment = appCompatActivity
                .supportFragmentManager
                .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun onActivityResult(requestCode: Int, data: Intent) {

        if (requestCode == MapComponent.PLACE_PICKER_REQUEST_FROM) {
            val place = PlacePicker.getPlace(appCompatActivity, data)

            onMapActionListener.setOrigin(place.address.toString())

            removeMarker(originMarkerId)
            val title = appCompatActivity.getString(R.string.map_info_origin)
            originMarkerId = placeMarkerOnMap(title, place.latLng)

            updateCameraPosition()

        } else if (requestCode == MapComponent.PLACE_PICKER_REQUEST_TO) {
            val place = PlacePicker.getPlace(appCompatActivity, data)

            onMapActionListener.setDestination(place.address.toString())

            removeMarker(destinationMarkerId)
            val title = appCompatActivity.getString(R.string.map_info_destination)
            destinationMarkerId = placeMarkerOnMap(title, place.latLng)

            updateCameraPosition()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appCompatActivity)

        map = googleMap
        map.setOnMarkerClickListener({
            false
        })

        setUpMap()
    }

    fun loadPlacePicker(isOrigin: Boolean) {

        val requestCode = if (isOrigin) PLACE_PICKER_REQUEST_FROM else PLACE_PICKER_REQUEST_TO
        val builder = PlacePicker.IntentBuilder()

        try {
            appCompatActivity.startActivityForResult(builder.build(appCompatActivity), requestCode)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(appCompatActivity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(appCompatActivity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MapComponent.LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(appCompatActivity) { location ->
            if (location != null) {
                currentLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

                onMapActionListener.setOrigin(getAddress(currentLatLng))
                originMarkerId = placeMarkerOnMap("origin", currentLatLng)
            }
        }
    }

    private fun placeMarkerOnMap(title: String, location: LatLng): String {

        val marker = map.addMarker(MarkerOptions()
                .position(location)
                .title(title))

        markers[marker.id] = marker

        return marker.id
    }

    private fun removeMarker(id: String) {
        markers[id]?.remove()
        markers.remove(id)
    }

    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder(appCompatActivity)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex + 1) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapComponent", e.localizedMessage)
        }

        return addressText
    }

    private fun updateCameraPosition() {
        val builder = LatLngBounds.Builder()
        for (marker in markers.values) {
            builder.include(marker.position)
        }
        val bounds = builder.build()
        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                MapComponent.CAMERA_CENTER_PADDING.toFloat(),
                appCompatActivity.resources.displayMetrics).toInt()
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
    }

}