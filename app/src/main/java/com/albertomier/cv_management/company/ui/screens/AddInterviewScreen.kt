package com.albertomier.cv_management.company.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.company.ui.view.AddCompanyActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.main.base.TopAppBarReturn

@Composable
fun AddInterviewScreen(viewModel: CompanyViewModel, activity: AddCompanyActivity) {
    val companyName: String by viewModel.companyName.observeAsState(initial = "")
    val companyPhone: String by viewModel.companyPhone.observeAsState(initial = "")
    val companyContactName: String by viewModel.companyContactName.observeAsState(initial = "")
    val companyContactPhone: String by viewModel.companyContactPhone.observeAsState(initial = "")
    val isAddCompanyEnabled: Boolean by viewModel.isAddCompanyEnabled.observeAsState(initial = false)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarReturn(
                title = "Añadir Empresa",
                activity = activity,
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Surface(
            shadowElevation = 9.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}