package com.albertomier.cv_management.main.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.albertomier.cv_management.home.ui.screens.HomeScreen
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.profile.ui.screens.ProfileScreen

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    bottomNavController: NavHostController,
    startDestination: String = Screens.Home.route,
    viewModel: HomeViewModel,
    listState: LazyListState,
    onItemSelected: (id: Int?) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = bottomNavController,
        startDestination = startDestination,
        route = "bottomRoute"
    ) {
        composable(Screens.Home.route) { HomeScreen(viewModel, listState, onItemSelected) }
        //composable(Screens.Profile.route) { ProfileScreen(viewModel, this@ProfileActivity) }
    }
}