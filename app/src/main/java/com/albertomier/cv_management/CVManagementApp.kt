package com.albertomier.cv_management

import android.app.Application
import com.albertomier.cv_management.core.utils.SharedPreferenceUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CVManagementApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        initComponents()
    }

    private fun initComponents() {

    }

    companion object {
        private lateinit var instance: CVManagementApp
        var sharedPreferenceUtils: SharedPreferenceUtils? = null

        fun getAppInstance(): CVManagementApp {
            return instance
        }
    }
}
