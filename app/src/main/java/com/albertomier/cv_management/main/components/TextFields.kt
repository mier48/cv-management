package com.albertomier.cv_management.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.albertomier.cv_management.core.extensions.radius

@Composable
fun TextFieldRounded(
    value: String,
    label: String,
    placeholder: String,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier.fillMaxWidth(),
    shape: Shape = 16.radius(),
    backgroundColor: Color = Color.Black,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(onNext = {
        focusManager.moveFocus(
            FocusDirection.Down
        )
    }),
    cursorColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    minLines: Int = 1,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    enabled: Boolean = true,
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
        minLines = minLines,
        maxLines = maxLines,
        onValueChange = { onValueChanged(it) },
        keyboardOptions = keyboardOptions,
        readOnly = readOnly,
        enabled = enabled
    )
}