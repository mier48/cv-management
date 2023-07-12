package com.albertomier.cv_management.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.main.components.WebView
import com.albertomier.cv_management.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url: String = intent.getStringExtra(Preferences.URL) ?: ""

        setContent {
            BaseTheme {
                WebView(url = url)
            }
        }
    }
}