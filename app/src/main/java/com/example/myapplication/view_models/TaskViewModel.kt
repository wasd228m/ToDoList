package com.example.myapplication.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

class TaskViewModel : ViewModel() {
    /* we use private set on our taskList so that it can't be
    mutated from the view */
    var taskList by mutableStateOf<List<Task>>(listOf())
        private set

    /* we use a pair here for simple error handling
    the first value stands for isError, and the second value is
    the error message. */
    fun addTask(body: String): Pair<Boolean, String> {
        // ...
    }


}