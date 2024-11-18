package com.example.myapplication.style

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class ToDO {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello world!")
        }
    }
}