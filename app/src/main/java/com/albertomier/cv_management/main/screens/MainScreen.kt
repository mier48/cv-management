package com.albertomier.cv_management.main.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.main.base.BottomAppBarMain
import com.albertomier.cv_management.main.base.TopAppBarMain
import com.albertomier.cv_management.main.navigation.BottomNavigation

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)


    MainScreenContent(
        mainNavHostController = navHostController,
        drawerState = drawerState,
        viewModel = viewModel
    )
}

@Composable
private fun MainScreenContent(
    mainNavHostController: NavHostController,
    drawerState: DrawerState,
    viewModel: HomeViewModel
) {
    val selectedItem = remember { mutableStateOf(0) }
    val bottomBarNavHostController = rememberNavController()
    val currentBottomRoute =
        bottomBarNavHostController.currentBackStackEntryFlow.collectAsState(initial = bottomBarNavHostController.currentBackStackEntry)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarMain()
        },
        bottomBar = {
            BottomAppBarMain(
                navHostController = bottomBarNavHostController,
                selectedItem = selectedItem,
                currentRoute = currentBottomRoute.value?.destination?.route
            )
        },
    ) { paddingValues ->
        Surface(
            shadowElevation = 9.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomNavigation(
                bottomNavController = bottomBarNavHostController,
                mainNavController = mainNavHostController,
                viewModel = viewModel
            )
        }
    }
}