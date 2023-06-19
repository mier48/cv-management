package com.albertomier.cv_management.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.albertomier.cv_management.home.ui.view.HomeScreen
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    bottomNavController: NavHostController,
    startDestination: String = Screens.Home.route,
    mainNavController: NavHostController,
    viewModel: HomeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = bottomNavController,
        startDestination = startDestination,
        route = "bottomRoute"
    ) {
        composable(Screens.Home.route) { HomeScreen(viewModel) }
    }
}