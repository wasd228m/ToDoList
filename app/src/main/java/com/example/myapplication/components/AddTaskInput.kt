package com.example.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myapplication.view_models.TaskViewModel

@Composable
fun AddTaskInput(viewModel: TaskViewModel) {
    // Focus manager allows us to clear the focus programmatically
    val focusManager = LocalFocusManager.current
    // Keyboard controller enables us to close the keyboard programmatically
    val keyboardController = LocalSoftwareKeyboardController.current

    // State for our new task body
    var body by remember { mutableStateOf("") }

    // States for managing errors
    var error by remember { mutableStateOf("") }
    var isErrorVisible by remember { mutableStateOf(false) }

    // Padding values
    val MEDIUM_PADDING = 16.dp
    val SMALL_PADDING = 8.dp

    Column(modifier = Modifier.padding(MEDIUM_PADDING, SMALL_PADDING)) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = body,
            onValueChange = {
                isErrorVisible = false
                body = it
            },
            label = { Text("Enter task") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    val (hasError, errorMessage) = viewModel.addTask(body)
                    error = errorMessage
                    isErrorVisible = hasError

                    if (!hasError) {
                        body = ""
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }),
            isError = isErrorVisible,
        )
        if (isErrorVisible) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}
