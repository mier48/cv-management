@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.albertomier.cv_management.ui.theme.Typography


@Composable
fun TopAppBarReturn(
    title: String,
    activity: Activity,
    color: Color = Color.Transparent,
    tint: Color = Color.Red
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
            IconButton(onClick = { activity.finish() }) {
                Icon(
                    tint = tint,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                )
            }
        }
    )
}