package com.albertomier.cv_management.splash.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.albertomier.cv_management.R
import com.albertomier.cv_management.main.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_twitter),
            contentDescription = "Splash icon",
            modifier = Modifier.size(88.dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(1000L)

        navController.apply {
            popBackStack()
            navigate(Screens.Main.route)
        }
    }
}