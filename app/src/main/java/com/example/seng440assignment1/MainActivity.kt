package com.example.seng440assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.seng440assignment1.ui.theme.SENG440Assignment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SENG440Assignment1Theme {
                MainScreen()
            }
        }
    }
}