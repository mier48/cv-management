package com.albertomier.cv_management.profile.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.base.Title
import com.albertomier.cv_management.main.components.DefaultButton
import com.albertomier.cv_management.main.components.TextFieldRounded
import com.albertomier.cv_management.profile.data.Experience
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddExperienceSheetContent(
    context: Context,
    mainViewModel: ProfileViewModel,
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    viewModel: ProfileViewModel,
    onAddClicked: () -> Unit
) {
    val exCompanyName: String by viewModel.exCompanyName.observeAsState(initial = "")
    val exJobTitle: String by viewModel.exJobTitle.observeAsState(initial = "")
    val exLocation: String by viewModel.exLocation.observeAsState(initial = "")
    val exStartDate: String by viewModel.exStartDate.observeAsState(initial = "")
    val exEndDate: String by viewModel.exEndDate.observeAsState(initial = "")
    val exDescription: String by viewModel.exDescription.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(title = "Añade la información de tu experiencia")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldRounded(
            value = exCompanyName,
            label = "Nombre de la empresa",
            placeholder = "Añade el nombre de la empresa"
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = value,
                jobTitle = exJobTitle,
                location = exLocation,
                startDate = exStartDate,
                endDate = exEndDate,
                description = exDescription
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = exJobTitle,
            label = "Puesto",
            placeholder = "Añade el puesto en la empresa"
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = exCompanyName,
                jobTitle = value,
                location = exLocation,
                startDate = exStartDate,
                endDate = exEndDate,
                description = exDescription
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = exLocation,
            label = "Localización",
            placeholder = "Añade la localización de la empresa"
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = exCompanyName,
                jobTitle = exJobTitle,
                location = value,
                startDate = exStartDate,
                endDate = exEndDate,
                description = exDescription
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = exStartDate,
            label = "Fecha de inicio",
            placeholder = "Añade la fecha de inicio en la empresa"
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = exCompanyName,
                jobTitle = exJobTitle,
                location = exLocation,
                startDate = value,
                endDate = exEndDate,
                description = exDescription
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = exEndDate,
            label = "Fecha de fin",
            placeholder = "Añade la fecha de fin en la empresa"
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = exCompanyName,
                jobTitle = exJobTitle,
                location = exLocation,
                startDate = exStartDate,
                endDate = value,
                description = exDescription
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = exDescription,
            label = "Descripción",
            placeholder = "Añade una descripción del puesto",
            singleLine = false,
            minLines = 3,
            maxLines = 6
        ) { value ->
            viewModel.onExperienceDataChanged(
                company = exCompanyName,
                jobTitle = exJobTitle,
                location = exLocation,
                startDate = exStartDate,
                endDate = exEndDate,
                description = value
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DefaultButton(
            text = "Añadir Experiencia",
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            onButtonClick = {
                val experience = Experience(
                    company = exCompanyName,
                    jobTitle = exJobTitle,
                    location = exLocation,
                    startDate = exStartDate,
                    endDate = exEndDate,
                    description = exDescription
                )

                viewModel.addExperienceItem(experience)

                scope.launch {
                    modalBottomSheetState.hide()
                }
                viewModel.resetExperienceFields()
            })
    }
}

@Composable
fun UpdateExperienceSheetContent(
    context: Context,
    mainViewModel: ProfileViewModel,
    onSaveClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {

    }
}