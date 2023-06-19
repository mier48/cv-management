package com.albertomier.cv_management.core.utils

import android.util.Log
import android.util.Patterns
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

object Utils {

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4

    fun enableRegister(name: String, email: String, password: String, confirmPassword: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4 && password == confirmPassword && name.length > 3

    fun getAuthorization(): String {
        Log.e("TAG", SharedPreferenceUtils().getStringValue(Preferences.ACCESS_TOKEN))
        return "Bearer " + SharedPreferenceUtils().getStringValue(Preferences.ACCESS_TOKEN)
    }

    fun customShape() =  object : Shape {
        override fun createOutline(
            size: androidx.compose.ui.geometry.Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            return Outline.Rectangle(Rect(0f,0f,100f /* width */, 131f /* height */))
        }
    }
}