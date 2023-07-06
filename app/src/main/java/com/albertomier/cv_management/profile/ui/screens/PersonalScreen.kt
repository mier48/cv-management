package com.albertomier.cv_management.profile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.components.DefaultButton
import com.albertomier.cv_management.main.components.TextFieldRounded
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel

@Composable
fun PersonalScreen(viewModel: ProfileViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val lastname: String by viewModel.lastname.observeAsState(initial = "")
    val birthdate: String by viewModel.birthdate.observeAsState(initial = "")
    val residencePlace: String by viewModel.residencePlace.observeAsState(initial = "")
    val jobTitle: String by viewModel.jobTitle.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val phone: String by viewModel.phone.observeAsState(initial = "")
    val languages: String by viewModel.languages.observeAsState(initial = "")
    val description: String by viewModel.description.observeAsState(initial = "")
    val isSaveDataEnabled: Boolean by viewModel.isSaveDataEnabled.observeAsState(initial = false)
    val isDataGetFromApi: Boolean by viewModel.isPersonalDataGetFromApi.observeAsState(initial = false)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldRounded(
            value = name,
            label = "Nombre",
            placeholder = "Añade el nombre"
        ) { value ->
            viewModel.onPersonalDataChanged(
                value,
                lastname,
                birthdate,
                residencePlace,
                jobTitle,
                email,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = lastname,
            label = "Apellidos",
            placeholder = "Añade los apellidos"
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                value,
                birthdate,
                residencePlace,
                jobTitle,
                email,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = birthdate,
            label = "Fecha de nacimiento",
            placeholder = "Añade la fecha de nacimiento"
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                value,
                residencePlace,
                jobTitle,
                email,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = residencePlace,
            label = "Lugar de residencia",
            placeholder = "Añade el lugar de residencia"
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                value,
                jobTitle,
                email,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = jobTitle,
            label = "Puesto de trabajo",
            placeholder = "Añade el puesto de trabajo"
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                residencePlace,
                value,
                email,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = email,
            label = "Email",
            placeholder = "Añade el email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                residencePlace,
                jobTitle,
                value,
                phone,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = phone,
            label = "Teléfono",
            placeholder = "Añade el teléfono",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                residencePlace,
                jobTitle,
                email,
                value,
                languages,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = languages,
            label = "Idiomas",
            placeholder = "Añade los idiomas que conoces"
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                residencePlace,
                jobTitle,
                email,
                phone,
                value,
                description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldRounded(
            value = description,
            label = "Descripción",
            placeholder = "Añade una brebe descripción",
            singleLine = false,
            minLines = 3,
            maxLines = 6,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) { value ->
            viewModel.onPersonalDataChanged(
                name,
                lastname,
                birthdate,
                residencePlace,
                jobTitle,
                email,
                phone,
                languages,
                value
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DefaultButton(
            text = if (isDataGetFromApi) "Guardar datos" else "Añadir datos",
            modifier = Modifier.fillMaxWidth(),
            enabled = isSaveDataEnabled,
            onButtonClick = {
                if (isDataGetFromApi) {
                    viewModel.updateData(
                        name = name,
                        lastname = lastname,
                        birthdate = birthdate,
                        residencePlace = residencePlace,
                        jobTitle = jobTitle,
                        email = email,
                        phone = phone,
                        languages = languages,
                        description = description
                    )
                } else {
                    viewModel.saveData(
                        name = name,
                        lastname = lastname,
                        birthdate = birthdate,
                        residencePlace = residencePlace,
                        jobTitle = jobTitle,
                        email = email,
                        phone = phone,
                        languages = languages,
                        description = description
                    )
                }
            })
    }
}