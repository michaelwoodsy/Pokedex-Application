package com.example.seng440assignment1.screens.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seng440assignment1.screens.GamesScreen
import com.example.seng440assignment1.screens.AboutScreen
import com.example.seng440assignment1.screens.MoviesScreen
import com.example.seng440assignment1.screens.RandomScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Episodes.route
    ) {
        composable(route = BottomBarScreen.Episodes.route) {
            AboutScreen()
        }
        composable(route = BottomBarScreen.Characters.route) {
            GamesScreen()
        }
        composable(route = BottomBarScreen.Locations.route) {
            MoviesScreen()
        }
        composable(route = BottomBarScreen.Watch.route) {
            RandomScreen()
        }
    }
}