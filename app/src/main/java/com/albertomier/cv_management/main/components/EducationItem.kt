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
import com.albertomier.cv_management.profile.domain.model.EducationData
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun EducationItem(item: EducationData, onClick: (item: EducationData) -> Unit) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { onClick(item) },
        shape = 8.radius(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            EducationItemRow("Centro de estudios: ", item.centerName)
            EducationItemRow("Título: ", item.title)
            EducationItemRow("Ubicación: ", item.location)
            EducationItemRow("Fecha de comienzo: ", item.startDate)
            EducationItemRow("Fecha de finalización: ", item.endDate)
            EducationItemRow("Descripción: ", item.description)
        }
    }
}

@Composable
fun EducationItemRow(title: String, value: String) {
    Row {
        Text(text = title, style = Typography.bodyMedium)
        Text(text = value, style = Typography.labelMedium)
    }
}