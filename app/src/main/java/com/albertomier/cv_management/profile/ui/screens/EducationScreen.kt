package com.albertomier.cv_management.profile.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.TopAppBarReturn
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel

@Composable
fun EducationScreen(viewModel: ProfileViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FabButton(text = "Añadir", isVisibleBecauseOfScrolling = true, onItemClick = {

            })
        }
    ) {

    }
}