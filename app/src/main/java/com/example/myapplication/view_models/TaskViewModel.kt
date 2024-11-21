package com.example.myapplication.view_models

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Task(val id: Int, val body: String)

class TaskViewModel : ViewModel() {
    // Список задач
    var taskList by mutableStateOf(listOf<Task>())
        private set

    // Добавление новой задачи
    fun addTask(body: String): Pair<Boolean, String> {
        return if (body.isNotBlank()) {
            val newTask = Task(id = taskList.size + 1, body = body)
            taskList = taskList + newTask
            Pair(false, "Task added successfully")
        } else {
            Pair(true, "Task body cannot be empty")
        }
    }
}
