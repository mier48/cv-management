package com.albertomier.cv_management.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.core.extensions.Height
import com.albertomier.cv_management.core.extensions.radius
import com.albertomier.cv_management.ui.theme.Green
import com.albertomier.cv_management.ui.theme.Red

@Composable
fun ItemInterview(
    item: InterviewItem,
    onItemSelected: (id: Int, date: String, hour: String, comment: String, done: Int) -> Unit
) {
    val bgColor = if (item.done == 1) Green else Red

    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(item.id, item.date, item.hour, item.comment, item.done) },
        shape = 8.radius(),
        backgroundColor = bgColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                RowData(text = item.date, imageVector = Icons.Filled.CalendarMonth)
                RowData(text = item.hour, imageVector = Icons.Filled.Timer)
            }
            8.Height()
            RowData(text = item.comment, imageVector = Icons.Filled.Note)
        }
    }
}