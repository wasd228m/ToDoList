package com.example.myapplication.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.components.AddTaskInput
import com.example.myapplication.components.TaskAppTopBar
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.view_models.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text("Hello, world!")
        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    MyTestApplicationTheme {
        TaskScreen(viewModel = TaskViewModel())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskScreenPreview() {
    MyTestApplicationTheme {
        TaskScreen(viewModel = TaskViewModel())
    }
}


@Composable
fun TaskScreen(viewModel: TaskViewModel) {/* our state doesn't toggle the dialog yet, but we'll come
    back to this */
    val deleteAllTasksDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TaskAppTopBar(deleteAllTasksDialog, isInputVisible)
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.backgroundColor)
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
        ) {
            isInputVisible.ExpandAndShrinkAnimation {
                AddTaskInput(viewModel)
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    items(items = viewModel.taskList, key = {
                        it.id
                    }) { task ->
                        TaskItem(viewModel, task)
                    }
                })
        }
    }