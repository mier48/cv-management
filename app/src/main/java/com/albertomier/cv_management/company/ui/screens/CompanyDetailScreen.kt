package com.albertomier.cv_management.company.ui.screens

import androidx.compose.foundation.layout.Row
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
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.company.ui.view.CompanyDetailActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.TopAppBarReturn
import com.albertomier.cv_management.main.components.ItemCompany

@Composable
fun CompanyDetailScreen(viewModel: CompanyViewModel, activity: CompanyDetailActivity) {
    val item: CompanyItem by viewModel.companyItem.observeAsState(initial = CompanyItem())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarReturn(
                title = item.name,
                activity = activity,
                tint = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.primary
            )
        },
        floatingActionButton = {
            FabButton(text = "Añadir entrevistas") {

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
            Row {
                ItemCompany(item = item, onItemSelected = {})
            }
        }
    }
}