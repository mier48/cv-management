package com.albertomier.cv_management.login.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertomier.cv_management.core.utils.AppThemeState
import com.albertomier.cv_management.core.utils.SystemUiController
import com.albertomier.cv_management.login.ui.screens.LoginScreen
import com.albertomier.cv_management.login.ui.screens.RegisterScreen
import com.albertomier.cv_management.login.ui.viewmodel.LoginViewModel
import com.albertomier.cv_management.main.navigation.Screens
import com.albertomier.cv_management.ui.theme.BaseTheme
import com.albertomier.cv_management.ui.theme.appBarDarkBlackDark
import com.albertomier.cv_management.ui.theme.appScaffoldColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            val appThemeState = appTheme.value

            systemUiController.setStatusBarColor(
                color = if (appThemeState.darkTheme) appBarDarkBlackDark else appScaffoldColor,
                darkIcons = !appThemeState.darkTheme
            )

            BaseTheme {
                val navigationController = rememberNavController()

                NavHost(
                    navController = navigationController,
                    startDestination = Screens.Login.route
                ) {
                    composable(Screens.Login.route) {
                        LoginScreen(loginViewModel, navigationController)
                    }
                    composable(Screens.Register.route) {
                        RegisterScreen(loginViewModel, navigationController)
                    }
                }
            }
        }
    }
}
