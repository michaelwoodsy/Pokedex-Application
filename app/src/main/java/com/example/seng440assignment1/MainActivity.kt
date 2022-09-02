package com.example.seng440assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.rememberNavController
import com.example.seng440assignment1.screens.home.MainScreen
import com.example.seng440assignment1.ui.theme.SENG440Assignment1Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SENG440Assignment1Theme {
                val systemUiController = rememberSystemUiController()
                val navBarColor = MaterialTheme.colors.primary
                SideEffect {
                    systemUiController.setSystemBarsColor(color = navBarColor)
                }
                MainScreen(navController = rememberNavController())
            }
        }
    }
}