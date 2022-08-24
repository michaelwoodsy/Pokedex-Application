package com.example.seng440assignment1.screens.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.seng440assignment1.R

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    object Episodes: BottomBarScreen(
        route = "about",
        title = R.string.about,
        icon = Icons.Default.Info
    )
    object Characters: BottomBarScreen(
        route = "games",
        title = R.string.games,
        icon = Icons.Default.List
    )
    object Locations: BottomBarScreen(
        route = "movies",
        title = R.string.movies,
        icon = Icons.Default.PlayArrow
    )
    object Watch: BottomBarScreen(
        route = "random",
        title = R.string.random,
        icon = Icons.Default.Star
    )
}
