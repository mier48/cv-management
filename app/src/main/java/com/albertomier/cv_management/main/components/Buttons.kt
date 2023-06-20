package com.albertomier.cv_management.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import com.albertomier.cv_management.core.extensions.radius

@Composable
fun ButtonWithIcon(
    text: String,
    icon: ImageVector,
    modifier: Modifier,
    shape: Shape = 16.radius(),
    onButtonClick: () -> Unit
) {
    Button(modifier = modifier, shape = shape, onClick = { onButtonClick() }) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text)
    }
}

@Composable
fun DefaultButton(
    text: String,
    modifier: Modifier,
    shape: Shape = 16.radius(),
    onButtonClick: () -> Unit,
    enabled: Boolean
) {
    Button(modifier = modifier, shape = shape, enabled = enabled, onClick = { onButtonClick() }) {
        Text(text)
    }
}