package com.example.weather.binding

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.weather.Constants
import com.example.weather.R

object DataBindingAdapters {
    @JvmStatic
    @BindingAdapter("app:imageCode")
    fun setImageCode(view: ImageView, imageCode: String?) {
        if (imageCode == null) return

        when (imageCode.dropLast(1)) {
            Constants.CLEAR_SKY -> view.setImageResource(R.drawable.ic_clear_sky)
            Constants.FEW_CLOUDS -> view.setImageResource(R.drawable.ic_few_clouds)
            Constants.SCATTERED_CLOUDS -> view.setImageResource(R.drawable.ic_scattered_clouds)
            Constants.BROKEN_CLOUDS -> view.setImageResource(R.drawable.ic_broken_clouds)
            Constants.SHOWER_RAIN -> view.setImageResource(R.drawable.ic_shower_rain)
            Constants.RAIN -> view.setImageResource(R.drawable.ic_rain)
            Constants.THUNDERSTORM -> view.setImageResource(R.drawable.ic_thunderstorm)
            Constants.SNOW -> view.setImageResource(R.drawable.ic_snow)
            Constants.MIST -> view.setImageResource(R.drawable.ic_mist)
        }
    }

    @JvmStatic
    @BindingAdapter("app:image")
    fun setImage(view: ImageView, image: String?) {
        if (image == null) return

        when (image.dropLast(1)) {
            Constants.CLEAR_SKY -> view.setImageResource(R.drawable.clear_sky)
            Constants.FEW_CLOUDS -> view.setImageResource(R.drawable.few_clouds)
            Constants.SCATTERED_CLOUDS -> view.setImageResource(R.drawable.scatterd_clouds)
            Constants.BROKEN_CLOUDS -> view.setImageResource(R.drawable.broken_clouds)
            Constants.SHOWER_RAIN -> view.setImageResource(R.drawable.shower_rain)
            Constants.RAIN -> view.setImageResource(R.drawable.rain)
            Constants.THUNDERSTORM -> view.setImageResource(R.drawable.thunderstorm)
            Constants.SNOW -> view.setImageResource(R.drawable.snow)
            Constants.MIST -> view.setImageResource(R.drawable.mist)
        }
    }

    @JvmStatic
    @BindingAdapter("app:viewVisibility")
    fun setViewVisibility(view: View, visibility: Boolean?) {
        if (visibility == null) return

        if (visibility) {
            view.visibility = View.VISIBLE
        }
        else {
            view.visibility = View.GONE
        }
    }
}