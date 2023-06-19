package com.albertomier.cv_management.core.utils

import com.albertomier.cv_management.CVManagementApp

object AppInstances {
    fun getSharedPreferencesInstance(): SharedPreferenceUtils {
        return if (CVManagementApp.sharedPreferenceUtils == null) {
            CVManagementApp.sharedPreferenceUtils = SharedPreferenceUtils()
            CVManagementApp.sharedPreferenceUtils!!
        } else {
            CVManagementApp.sharedPreferenceUtils!!
        }
    }
}