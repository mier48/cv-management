package com.albertomier.cv_management.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.albertomier.cv_management.core.extensions.radius
import com.albertomier.cv_management.ui.theme.primaryTextStyle
import com.albertomier.cv_management.ui.theme.secondaryTextStyle

@Composable
fun TextFieldRounded(
    value: String,
    label: String,
    placeholder: String,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier.fillMaxWidth(),
    shape: Shape = 16.radius(),
    backgroundColor: Color = Color.Black,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    cursorColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onValueChanged: (value: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        shape = shape,
        singleLine = singleLine,
        onValueChange = { onValueChanged(it) },
        keyboardOptions = keyboardOptions,
    )
}