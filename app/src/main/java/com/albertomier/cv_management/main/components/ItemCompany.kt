package com.albertomier.cv_management.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.extensions.Height
import com.albertomier.cv_management.core.extensions.Width
import com.albertomier.cv_management.core.extensions.radius
import com.albertomier.cv_management.core.extensions.validate
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun ItemCompany(item: CompanyItem, onItemSelected: (id: Int?) -> Unit) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(item.id) },
        shape = 8.radius(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text("Datos de la empresa", style = Typography.titleLarge)
            }
            8.Height()
            RowData(text = item.name, imageVector = Icons.Filled.Home)
            RowData(text = item.email, imageVector = Icons.Filled.Email)
            RowData(
                text = item.phone,
                imageVector = Icons.Filled.Phone,
                style = Typography.labelLarge
            )

            16.Height()

            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text("Datos de contacto", style = Typography.titleLarge)
            }
            RowData(text = item.contactName, imageVector = Icons.Filled.Person)
            RowData(text = item.contactEmail, imageVector = Icons.Filled.Email)
            RowData(
                text = item.contactPhone,
                imageVector = Icons.Filled.Phone,
                style = Typography.labelLarge
            )
        }
    }
}

@Composable
fun RowData(text: String, imageVector: ImageVector, style: TextStyle = Typography.bodyLarge) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = imageVector, contentDescription = text.validate())
        8.Width()
        Text(text.validate(), style = style)
    }
}