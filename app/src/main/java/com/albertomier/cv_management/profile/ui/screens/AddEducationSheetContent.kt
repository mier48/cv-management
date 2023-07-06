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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.base.Title
import com.albertomier.cv_management.main.components.DefaultButton
import com.albertomier.cv_management.main.components.TextFieldRounded
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddEducationSheetContent(
    context: Context,
    mainViewModel: ProfileViewModel,
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    viewModel: ProfileViewModel,
    update: Boolean = false,
    onAddClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val edSchool: String by viewModel.edSchool.observeAsState(initial = "")
    val edTitle: String by viewModel.edTitle.observeAsState(initial = "")
    val edLocation: String by viewModel.edLocation.observeAsState(initial = "")
    val edStartDate: String by viewModel.edStartDate.observeAsState(initial = "")
    val edEndDate: String by viewModel.edEndDate.observeAsState(initial = "")
    val edDescription: String by viewModel.edDescription.observeAsState(initial = "")
    val isDataGetFromApi: Boolean by viewModel.isEducationDataGetFromApi.observeAsState(initial = false)
    val isSaveEducationDataEnabled: Boolean by viewModel.isSaveEducationDataEnabled.observeAsState(
        initial = false
    )

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
            Title(title = if (update) "Modifica la información de tus estudios" else "Añade la información de tus estudios")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldRounded(
            value = edSchool,
            label = "Centro de estudios",
            placeholder = "Añade el nombre del centro de estudios"
        ) { value ->
            viewModel.onEducationDataChanged(
                school = value,
                title = edTitle,
                location = edLocation,
                startDate = edStartDate,
                endDate = edEndDate,
                description = edDescription,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = edTitle,
            label = "Título",
            placeholder = "Añade el título"
        ) { value ->
            viewModel.onEducationDataChanged(
                school = edSchool,
                title = value,
                location = edLocation,
                startDate = edStartDate,
                endDate = edEndDate,
                description = edDescription,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = edLocation,
            label = "Localización",
            placeholder = "Añade la localización del centro de estudios"
        ) { value ->
            viewModel.onEducationDataChanged(
                school = edSchool,
                title = edTitle,
                location = value,
                startDate = edStartDate,
                endDate = edEndDate,
                description = edDescription,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = edStartDate,
            label = "Fecha de inicio",
            placeholder = "Añade la fecha de inicio"
        ) { value ->
            viewModel.onEducationDataChanged(
                school = edSchool,
                title = edTitle,
                location = edLocation,
                startDate = value,
                endDate = edEndDate,
                description = edDescription,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = edEndDate,
            label = "Fecha de fin",
            placeholder = "Añade la fecha de fin"
        ) { value ->
            viewModel.onEducationDataChanged(
                school = edSchool,
                title = edTitle,
                location = edLocation,
                startDate = edStartDate,
                endDate = value,
                description = edDescription,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = edDescription,
            label = "Descripción",
            placeholder = "Añade una descripción del título",
            singleLine = false,
            minLines = 3,
            maxLines = 6,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        ) { value ->
            viewModel.onEducationDataChanged(
                school = edSchool,
                title = edTitle,
                location = edLocation,
                startDate = edStartDate,
                endDate = edEndDate,
                description = value,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DefaultButton(
            text = if (update) "Modificar datos" else "Añadir datos",
            modifier = Modifier.fillMaxWidth(),
            enabled = isSaveEducationDataEnabled,
            onButtonClick = {
                if (update) {
                    viewModel.updateEducationData(
                        school = edSchool,
                        title = edTitle,
                        location = edLocation,
                        startDate = edStartDate,
                        endDate = edEndDate,
                        description = edDescription
                    )
                } else {
                    viewModel.saveEducationData(
                        school = edSchool,
                        title = edTitle,
                        location = edLocation,
                        startDate = edStartDate,
                        endDate = edEndDate,
                        description = edDescription
                    )
                }

                scope.launch {
                    modalBottomSheetState.hide()
                }
                viewModel.resetEducationFields()
            })
    }
}

@Composable
fun UpdateEducationSheetContent(
    context: Context,
    mainViewModel: ProfileViewModel,
    onSaveClicked: () -> Unit
) {

}