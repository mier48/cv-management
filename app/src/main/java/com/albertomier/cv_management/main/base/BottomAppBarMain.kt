@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.albertomier.cv_management.main.navigation.Screens
import com.albertomier.cv_management.ui.theme.Black
import com.albertomier.cv_management.ui.theme.Blue
import kotlin.random.Random

@Composable
fun BottomAppBarMain(
    navHostController: NavHostController,
    selectedItem: MutableState<Int>,
    currentRoute: String?
) {
    data class BottomAppBarItem(
        val route: String,
        val isBadged: Boolean = Random.nextBoolean(),
        val icon: ImageVector
    )

    fun navigate(route: String) {
        navHostController.backQueue.clear()
        navHostController.navigate(route)
    }

    val items = listOf(
        BottomAppBarItem(
            route = Screens.Home.route,
            icon = Icons.Filled.Home
        ),
        BottomAppBarItem(
            route = Screens.Profile.route,
            icon = Icons.Filled.Note
        ),
        BottomAppBarItem(
            route = Screens.Profile.route,
            icon = Icons.Filled.CalendarMonth
        )
    )
    BottomAppBar(
        modifier = Modifier.height(56.dp),
        contentColor = MaterialTheme.colorScheme.surface,
        containerColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                items.forEachIndexed { index, item ->
                    IconButton(
                        onClick = {
                            if (currentRoute != item.route) {
                                selectedItem.value = index
                                navigate(item.route)
                            }
                        },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint =
                            if (currentRoute == item.route) MaterialTheme.colorScheme.surface
                            else Black
                        )
                    }
                }
            }
        }
    )
}