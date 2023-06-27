@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun ShowProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(100.dp))
    }
}

@Composable
fun DefaultTopAppBar(
    title: String,
    activity: Activity,
    color: Color = Color.Transparent,
    tint: Color = Color.Black
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = color),
        title = {
            Text(
                text = title,
                color = tint,
                style = Typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { activity.finish() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back button",
                    tint = tint
                )
            }
        }
    )
}

@Composable
fun FabButton(
    text: String,
    imageVector: ImageVector = Icons.Default.Create,
    isVisibleBecauseOfScrolling: Boolean = false,
    onItemClick: () -> Unit,
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isVisibleBecauseOfScrolling,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = fadeOut(
            animationSpec = keyframes {
                this.durationMillis = 120
            }
        )
    ) {
        ExtendedFloatingActionButton(
            text = {
                Text(text = text, color = Color.White)
            },
            icon = {
                Icon(imageVector = imageVector, tint = Color.White, contentDescription = text)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = { onItemClick() },
        )
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}