@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.albertomier.cv_management.ui.theme.Typography


@Composable
fun TopAppBarReturn(
    title: String,
    activity: Activity,
    color: Color = Color.Transparent,
    tint: Color = Color.Red,
    navigationController: NavHostController? = null
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
            titleContentColor = tint
        ),
        title = {
            Text(text = title, color = tint, style = Typography.titleLarge)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    if (navigationController == null) {
                        activity.finish()
                    } else {
                        navigationController.popBackStack()
                    }
                }
            ) {
                Icon(
                    tint = tint,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                )
            }
        }
    )
}

@Composable
fun TopAppBarReturnWithAction(
    title: String,
    activity: Activity,
    color: Color = Color.Transparent,
    tint: Color = Color.Red,
    navigationController: NavHostController? = null,
    icon: ImageVector,
    onButtonClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
            titleContentColor = tint
        ),
        title = {
            Text(text = title, color = tint, style = Typography.titleLarge)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    if (navigationController == null) {
                        activity.finish()
                    } else {
                        navigationController.popBackStack()
                    }
                }
            ) {
                Icon(
                    tint = tint,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                )
            }
        },
        actions = {
            IconButton(onClick = { onButtonClick() }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = tint
                )
            }
        }
    )
}