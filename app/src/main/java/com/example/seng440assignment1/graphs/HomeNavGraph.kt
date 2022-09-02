package com.example.seng440assignment1.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seng440assignment1.screens.about.AboutScreen
import com.example.seng440assignment1.screens.guesspokemon.GuessPokemonScreen
import com.example.seng440assignment1.screens.home.BottomBarScreen
import com.example.seng440assignment1.screens.pokemonlist.PokemonDetailScreen
import com.example.seng440assignment1.screens.pokemonlist.PokemonListScreen
import java.util.*

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.About.route
    ) {
        composable(route = BottomBarScreen.About.route) {
            AboutScreen()
        }
        composable(route = BottomBarScreen.Games.route) {
            GuessPokemonScreen()
        }
        pokedexNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.pokedexNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.POKEDEX,
        startDestination = BottomBarScreen.Pokedex.route
    ) {
        composable(route = BottomBarScreen.Pokedex.route) {
            PokemonListScreen(navController = navController)
        }
        composable(
            "pokemon_details_screen/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController,
            )
        }
    }
}

object Graph {
    const val POKEDEX = "pokedex_graph"
}