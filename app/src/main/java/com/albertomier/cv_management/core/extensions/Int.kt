package com.albertomier.cv_management.core.extensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Height Extension
@Composable
fun Int.Height() = Spacer(modifier = Modifier.height(this.dp))

// Weight Extension
@Composable
fun Int.Width() = Spacer(modifier = Modifier.width(this.dp))

// Rounded corner All Extension
@Composable
fun Int.radius() = RoundedCornerShape(this.dp)

// Integer null check extensions
fun Int?.validate(value: Int = 0): Int {
    return this ?: value
}

/**
 * TODO
 *
 * @return
 */
fun Int.toNumberString() = numberToString(this)

/**
 * TODO
 *
 * @param number
 * @return
 */
private fun numberToString(number: Int): String {
    return if (number < 10) {
        "0$number"
    } else {
        number.toString()
    }
}
