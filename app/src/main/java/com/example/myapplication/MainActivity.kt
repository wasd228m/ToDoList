package com.example.myapplication

import android.os.Bundle
import com.example.myapplication.screens.TaskScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication.view_models.TaskViewModel


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TaskViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestApplicationTheme {
                TaskScreen(viewModel)

            }
        }
    }
}

