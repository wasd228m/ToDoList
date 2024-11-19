package com.example.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.myapplication.view_models.TaskViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskInput(viewModel: TaskViewModel) {
    // focusManager allows us to clear the focus programmatically
    val focusManager = LocalFocusManager.current
    // keyboardController enables us to close the keyboard programmatically
    val keyboardController = LocalSoftwareKeyboardController.current

    // state for our new task body
    var body by remember {
        mutableStateOf("")
    }

    // states for managing errors
    var error by remember { mutableStateOf("") }
    var isErrorVisible by remember { mutableStateOf(false) }

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