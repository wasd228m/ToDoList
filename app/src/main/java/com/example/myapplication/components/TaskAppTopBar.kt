package com.example.myapplication.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAppTopBar(
    deleteAllTasksDialog: MutableState<Boolean>,
    isInputVisible: MutableState<Boolean>,
) {
    TopAppBar(title = {
        Text(text = "My Task App")
    },
        actions = {
            // Add our new icon button to the actions
            IconButton(onClick = {
                isInputVisible.value = !isInputVisible.value
            }) {
                // If the input is visible, show the Clear icon
                if (isInputVisible.value) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = MaterialTheme.colorScheme.error
                    )
                    // If the input is NOT visible, show the Add icon
                } else {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            IconButton(onClick = {
                deleteAllTasksDialog.value = true
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

                colors = TopAppBarDefaults . topAppBarColors (containerColor =
            MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary)
        )
    )
}