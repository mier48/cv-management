@file:OptIn(ExperimentalPagerApi::class)

package com.albertomier.cv_management.company.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.company.ui.view.AddCompanyActivity
import com.albertomier.cv_management.company.ui.viewmodel.CompanyViewModel
import com.albertomier.cv_management.main.base.TopAppBarReturn
import com.albertomier.cv_management.main.components.DefaultButton
import com.albertomier.cv_management.main.components.TextFieldRounded
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun AddCompanyScreen(viewModel: CompanyViewModel, activity: AddCompanyActivity) {
    val companyName: String by viewModel.companyName.observeAsState(initial = "")
    val companyPhone: String by viewModel.companyPhone.observeAsState(initial = "")
    val companyEmail: String by viewModel.companyEmail.observeAsState(initial = "")
    val companyContactName: String by viewModel.companyContactName.observeAsState(initial = "")
    val companyContactPhone: String by viewModel.companyContactPhone.observeAsState(initial = "")
    val companyContactEmail: String by viewModel.companyContactEmail.observeAsState(initial = "")
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
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldRounded(
                    value = companyName,
                    label = "Nombre de la empresa",
                    placeholder = "Añade el nombre de la empresa"
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = value,
                        companyPhone = companyPhone,
                        companyEmail = companyEmail,
                        companyContactName = companyContactName,
                        companyContactPhone = companyContactPhone,
                        companyContactEmail = companyContactEmail
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyPhone,
                    label = "Teléfono de la empresa",
                    placeholder = "Añade el teléfono de la empresa",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = companyName,
                        companyPhone = value,
                        companyEmail = companyEmail,
                        companyContactName = companyContactName,
                        companyContactPhone = companyContactPhone,
                        companyContactEmail = companyContactEmail
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyEmail,
                    label = "Email de la empresa",
                    placeholder = "Añade el email de la empresa",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = companyName,
                        companyPhone = companyPhone,
                        companyEmail = value,
                        companyContactName = companyContactName,
                        companyContactPhone = companyContactPhone,
                        companyContactEmail = companyContactEmail
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyContactName,
                    label = "Nombre de contacto",
                    placeholder = "Añade el nombre de contacto"
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = companyName,
                        companyPhone = companyPhone,
                        companyEmail = companyEmail,
                        companyContactName = value,
                        companyContactPhone = companyContactPhone,
                        companyContactEmail = companyContactEmail
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyContactPhone,
                    label = "Teléfono de contacto",
                    placeholder = "Añade el teléfono de contacto",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = companyName,
                        companyPhone = companyPhone,
                        companyEmail = companyEmail,
                        companyContactName = companyContactName,
                        companyContactPhone = value,
                        companyContactEmail = companyContactEmail
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldRounded(
                    value = companyContactEmail,
                    label = "Email de contacto",
                    placeholder = "Añade el email de contacto",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                ) { value ->
                    viewModel.onDataChanged(
                        companyName = companyName,
                        companyPhone = companyPhone,
                        companyEmail = companyEmail,
                        companyContactName = companyContactName,
                        companyContactPhone = companyContactPhone,
                        companyContactEmail = value
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                DefaultButton(
                    text = "Añadir Empresa",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isAddCompanyEnabled,
                    onButtonClick = {
                        viewModel.addCompanyData(
                            companyName = companyName,
                            companyPhone = companyPhone,
                            companyEmail = companyEmail,
                            companyContactName = companyContactName,
                            companyContactPhone = companyContactPhone,
                            companyContactEmail = companyContactEmail
                        )
                        activity.finish()
                    })
            }
        }
    }
}

