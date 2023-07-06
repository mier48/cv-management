package com.albertomier.cv_management.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.albertomier.cv_management.core.extensions.Width
import com.albertomier.cv_management.core.extensions.validate
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun RowData(text: String, imageVector: ImageVector, style: TextStyle = Typography.bodyLarge) {
    Row {
        Icon(imageVector = imageVector, contentDescription = text.validate())
        8.Width()
        Text(text.validate(), style = style)
    }
}