package com.example.weather.ui

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.Constants
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.utils.PermissionUtil
import com.example.weather.viewmodels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.view_changed_city.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private lateinit var map: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.apply {
            viewmodel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnShow.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra(Constants.INTENT_CITY, mainViewModel.changedCity.get()?.name)
            startActivity(intent)
        }

        mainViewModel.currentLocation.observe(this, Observer {
            currentLocation = it
            moveCamera(locationToLatLng(it))
        })

        if (!PermissionUtil.checkPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            PermissionUtil.requestPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (PermissionUtil.checkPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            mainViewModel.getLocation()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.silver_map))
        googleMap.setOnMapClickListener(this)

        enableMyLocation()
        mainViewModel.getLocation()
    }

    override fun onMapClick(latLng: LatLng) {
        map.clear()

        val marker = MarkerOptions().apply {
            icon(BitmapDescriptorFactory.fromResource(R.drawable.place))
            position(latLng)
        }

        mainViewModel.changePoint(latLng)
        map.addMarker(marker)
    }

    private fun locationToLatLng(location: Location): LatLng = LatLng(location.latitude, location.longitude)

    private fun moveCamera(point: LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15f))
    }

    private fun enableMyLocation() {
        if (PermissionUtil.checkPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            map.isMyLocationEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = false
        }
    }
}
