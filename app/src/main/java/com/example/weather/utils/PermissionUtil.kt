package com.example.weather.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.weather.Constants

object PermissionUtil {
    fun requestPermission(activity: Activity, permission: String) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), Constants.PERMISSION_REQUEST_CODE)
    }

    fun checkPermission(context: Context, permission: String): Boolean =
        ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}