package com.albertomier.cv_management.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SimpleAlertDialog(
    title: String,
    description: String,
    buttonText: String,
    openDialog: MutableState<Boolean>
) {
    if (openDialog.value) {
        AlertDialog(
            textContentColor = MaterialTheme.colorScheme.onPrimary,
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = title,
                    color = Color(0xFF504E44)
                )
            },
            text = {
                Text(
                    text = description,
                    color = Color(0xFF504E44)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = buttonText,
                        color = Color(0xFF504E44)
                    )
                }
            }
        )
    }
}