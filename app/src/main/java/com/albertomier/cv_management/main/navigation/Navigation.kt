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
    viewModel: HomeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        route = "mainRoute"
    ) {
        //composable(Splash.route) { SplashScreen(navController) }
        composable(Screens.Main.route) {
            MainScreen(
                navHostController = navController,
                viewModel = viewModel
            )
        }
//        composable(AuthorDetail.route) { AuthorScreen(navController) }
//        composable(Profile.route) { ProfileScreen(navController) }
//        composable(AddTweet.route) { AddTweetScreen(navController) }
//        composable(Followers.route) { FollowersScreen(navController, "followers") }
//        composable(Following.route) { FollowersScreen(navController, "following") }
//        composable(MessageDetail.route) { MessageDetailScreen(navController) }
//        composable(Settings.route) { SettingsScreen(navController) }
    }
}