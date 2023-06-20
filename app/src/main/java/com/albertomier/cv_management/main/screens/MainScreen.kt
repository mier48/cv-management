package com.albertomier.cv_management.main.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.albertomier.cv_management.home.ui.viewmodel.HomeViewModel
import com.albertomier.cv_management.main.base.BottomAppBarMain
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.TopAppBarMain
import com.albertomier.cv_management.main.navigation.BottomNavigation

@Composable
fun MainScreen(
    viewModel: HomeViewModel,
    onItemSelected: (id: Int?) -> Unit,
    onFabButtonClick: () -> Unit
) {
    MainScreenContent(
        viewModel = viewModel,
        onItemSelected = onItemSelected,
        onFabButtonClick = onFabButtonClick
    )
}

@Composable
private fun MainScreenContent(
    viewModel: HomeViewModel,
    onItemSelected: (id: Int?) -> Unit,
    onFabButtonClick: () -> Unit
) {
    val selectedItem = remember { mutableStateOf(0) }
    val bottomBarNavHostController = rememberNavController()
    val currentBottomRoute =
        bottomBarNavHostController.currentBackStackEntryFlow.collectAsState(initial = bottomBarNavHostController.currentBackStackEntry)
    val listState = rememberLazyListState()
    val fabVisibility by derivedStateOf {
        listState.firstVisibleItemIndex == 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarMain(
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary
            )
        },
        bottomBar = {
            BottomAppBarMain(
                navHostController = bottomBarNavHostController,
                selectedItem = selectedItem,
                currentRoute = currentBottomRoute.value?.destination?.route
            )
        },
        floatingActionButton = {
            FabButton(
                text = "Añadir Empresa",
                isVisibleBecauseOfScrolling = fabVisibility
            ) { onFabButtonClick() }
        }
    ) { paddingValues ->
        Surface(
            shadowElevation = 10.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomNavigation(
                bottomNavController = bottomBarNavHostController,
                viewModel = viewModel,
                onItemSelected = onItemSelected,
                listState = listState
            )
        }
    }
}