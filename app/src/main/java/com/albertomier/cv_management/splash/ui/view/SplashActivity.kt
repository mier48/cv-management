package com.albertomier.cv_management.splash.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.albertomier.cv_management.core.utils.AppThemeState
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.core.utils.SharedPreferenceUtils
import com.albertomier.cv_management.core.utils.SystemUiController
import com.albertomier.cv_management.login.ui.view.LoginActivity
import com.albertomier.cv_management.main.view.MainActivity
import com.albertomier.cv_management.ui.theme.appBarDarkBlackDark
import com.albertomier.cv_management.ui.theme.appScaffoldColor
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            val appThemeState = appTheme.value

            systemUiController.setStatusBarColor(
                color = if (appThemeState.darkTheme) appBarDarkBlackDark else appScaffoldColor,
                darkIcons = !appThemeState.darkTheme
            )
        }

        lifecycleScope.launchWhenCreated {
            delay(1000)

            if (SharedPreferenceUtils().containsValue(Preferences.ACCESS_TOKEN)) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}