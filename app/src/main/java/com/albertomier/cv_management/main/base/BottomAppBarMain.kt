@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.albertomier.cv_management.R
import com.albertomier.cv_management.main.navigation.Screens
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
        val icon: Int
    )

    fun navigate(route: String) {
        navHostController.backQueue.clear()
        navHostController.navigate(route)
    }

    val items = listOf(
        BottomAppBarItem(
            route = Screens.Home.route,
            icon = R.drawable.ic_home
        )
    )
    BottomAppBar(
        modifier = Modifier.height(56.dp),
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = MaterialTheme.colorScheme.background,
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
                        BadgedBox(
                            badge = {
                                if (item.isBadged) Badge(containerColor = Blue)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = null,
                                tint =
                                if (currentRoute == item.route) Blue
                                else MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    )
}