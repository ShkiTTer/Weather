package com.example.weather.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.Constants
import com.example.weather.NetworkState
import com.example.weather.R
import com.example.weather.databinding.ActivityWeatherBinding
import com.example.weather.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var viewModel: WeatherViewModel
    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_weather
        )

        city = intent.extras?.getString(Constants.INTENT_CITY)!!
        title = city

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        binding.apply {
            lifecycleOwner = this@WeatherActivity
            weather = viewModel.currentWeather
            networkState = viewModel.networkState
        }

        getWeather(city)

        swipeRefresh.setOnRefreshListener {
            getWeather(city)
        }

        viewModel.networkState.observe(this, Observer {
            if (it == NetworkState.ERROR) {
                showError()
            }
        })
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.home) {
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun getWeather(city: String) {
        viewModel.getWeather(city)
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_title)
            .setMessage(R.string.error_message)
            .setPositiveButton(R.string.error_repeat_button) { dialog, which ->
                getWeather(city)
            }
            .create().show()
    }
}
