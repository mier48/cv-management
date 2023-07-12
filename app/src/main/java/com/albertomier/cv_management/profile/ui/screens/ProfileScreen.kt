package com.albertomier.cv_management.profile.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.base.TopAppBarReturnWithAction
import com.albertomier.cv_management.profile.ui.view.ProfileActivity
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    activity: ProfileActivity,
    onExportFinished: (Uri) -> Unit
) {
    val htmlContent: String by viewModel.htmlContent.observeAsState(initial = "")
    val context = LocalContext.current

    with(viewModel) {
        getPersonalData()
        getEducationData()
        getExperienceData()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarReturnWithAction(
                title = "Configurar CV",
                activity = activity,
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary,
                icon = Icons.Filled.DocumentScanner
            ) {
                Log.e("AMIER", "HTML: $htmlContent")
                viewModel.generateV2()
                viewModel.saveCVFile(context, htmlContent) { fileUri ->
                    Log.e("AMIER", "PATH: ${fileUri.path}")
                    onExportFinished(fileUri)
                }
            }
        }
    ) {
        Surface(
            shadowElevation = 9.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            TabsWithStyle(viewModel)
        }
    }
}

@Composable
fun TabsWithStyle(viewModel: ProfileViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Personal", "Estudios", "Experiencia")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }

        when (tabIndex) {
            0 -> PersonalScreen(viewModel)
            1 -> EducationScreen(viewModel)
            2 -> ExperienceScreen(viewModel)
        }
    }
}

