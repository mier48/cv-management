package com.albertomier.cv_management.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.core.extensions.radius
import com.albertomier.cv_management.profile.domain.model.ExperienceData
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun ExperienceItem(item: ExperienceData, onClick: (item: ExperienceData) -> Unit) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { onClick(item) },
        shape = 8.radius(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ExperienceItemRow("Empresa: ", item.company)
            ExperienceItemRow("Puesto: ", item.jobTitle)
            ExperienceItemRow("Ubicación: ", item.location)
            ExperienceItemRow("Fecha de comienzo: ", item.startDate)
            ExperienceItemRow("Fecha de finalización: ", item.endDate)
            ExperienceItemRow("Descripción: ", item.description)
        }
    }
}

@Composable
fun ExperienceItemRow(title: String, value: String) {
    Row {
        Text(text = title, style = Typography.bodyMedium)
        Text(text = value, style = Typography.labelMedium)
    }
}