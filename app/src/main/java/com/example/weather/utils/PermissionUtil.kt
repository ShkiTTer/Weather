package com.example.weather.utils

import android.app.Activity
import android.content.pm.PackageManager

class PermissionUtil(private val activity: Activity) {
    fun requestPermission(permission: String) {
        activity.requestPermissions(arrayOf(permission), 1)
    }

    fun checkPermission(permission: String): Boolean =
        activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}