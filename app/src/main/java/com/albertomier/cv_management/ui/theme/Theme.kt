package com.albertomier.cv_management.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.albertomier.cv_management.core.utils.TextColor.textPrimaryDarkColor
import com.albertomier.cv_management.core.utils.TextColor.textPrimaryLightColor
import com.albertomier.cv_management.core.utils.TextColor.textSecondaryDarkColor
import com.albertomier.cv_management.core.utils.TextColor.textSecondaryLightColor

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    surface = Black,
    primaryContainer = Black,
    secondary = ExtraLightGray,
    onSecondary = ExtraLightGray,
    tertiary = ExtraLightGray,
    onTertiary = ExtraLightGray,
    background = Black,
    onBackground = ExtraLightGray,
    onSurface = LightGray,
    secondaryContainer = DarkDarkGray
)

private val LightColorScheme = lightColorScheme(
    primary = appRedColor2,
    onPrimary = Black,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = White,
    onBackground = Black,
    surface = White,
    secondaryContainer = ExtraExtraLightGray
)

@SuppressLint("ConflictingOnColor")
private val DarkRedColorPalette = darkColorScheme(
    primary = appRedColor,
    surface = appRedColor,
    onPrimary = textPrimaryDarkColor,
    onSecondary = textSecondaryDarkColor,
    onBackground = cardBackgroundBlackDark,
    secondary = cardBackgroundBlackDark,
    background = scaffoldDarkBlackDark
)

@SuppressLint("ConflictingOnColor")
private val LightRedColorPalette = lightColorScheme(
    primary = appRedColor,
    onPrimary = textPrimaryLightColor,
    onSecondary = textSecondaryLightColor,
    onBackground = appBackgroundColor,
    secondary = appLightGreyColor,
    background = appScaffoldColor

)

@Composable
fun BaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}