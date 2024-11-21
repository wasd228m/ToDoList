package com.example.myapplication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

// Модель Task
data class Task(
    val id: Int,
    val body: String,
    val completed: Boolean
)

// Константы для отступов
val SMALL_PADDING = 8.dp
val MEDIUM_PADDING = 16.dp

@Composable
fun TaskCard(
    task: Task,
    toggleCompleted: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(SMALL_PADDING)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(SMALL_PADDING)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(start = MEDIUM_PADDING)
                    .weight(1f),
                text = task.body,
                style = TextStyle(
                    textDecoration = if (task.completed) TextDecoration.LineThrough
                    else TextDecoration.None
                )
            )
            Checkbox(checked = task.completed, onCheckedChange = {
                toggleCompleted(task)
            })
        }
    }
}
