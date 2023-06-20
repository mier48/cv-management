package com.albertomier.cv_management.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.main.screens.MainScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Main.route,
    viewModel: HomeViewModel,
    onItemSelected: (id: Int?) -> Unit,
    onFabButtonClick: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        route = "mainRoute"
    ) {
        composable(Screens.Main.route) {
            MainScreen(
                viewModel = viewModel,
                onItemSelected = onItemSelected,
                onFabButtonClick = onFabButtonClick
            )
        }
    }
}