package com.example.myapplication.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.components.AddTaskInput
import com.example.myapplication.components.TaskAppTopBar
import com.example.myapplication.components.TaskItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view_models.TaskViewModel


@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    // State for dialog visibility
    val deleteAllTasksDialog = remember { mutableStateOf(false) }
    // State for input visibility
    val isInputVisible = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TaskAppTopBar(deleteAllTasksDialog, isInputVisible)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
        ) {
            // Conditional visibility of AddTaskInput
            if (isInputVisible.value) {
                AddTaskInput(viewModel)
            }
            // Task list
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    items(
                        items = viewModel.taskList,
                        key = { it.id }
                    ) { task ->
                        TaskItem(viewModel, task)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
        MyApplicationTheme{
        TaskScreen(viewModel = TaskViewModel())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskScreenPreview() {
        MyApplicationTheme {
        TaskScreen(viewModel = TaskViewModel())
    }
}
