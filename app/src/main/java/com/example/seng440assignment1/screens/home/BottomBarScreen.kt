package com.example.seng440assignment1.screens.home

import com.example.seng440assignment1.R

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: Int
) {
    object About: BottomBarScreen(
        route = "about",
        title = R.string.about,
        icon = R.drawable.outline_home_24
    )
    object Games: BottomBarScreen(
        route = "games",
        title = R.string.games,
        icon = R.drawable.outline_sports_esports_24
    )
    object Pokedex: BottomBarScreen(
        route = "pokedex",
        title = R.string.pokedex,
        icon = R.drawable.outline_catching_pokemon_24
    )
}
