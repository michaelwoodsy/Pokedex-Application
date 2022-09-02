package com.example.seng440assignment1.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.BlendMode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seng440assignment1.data.models.PokedexListEntry
import com.example.seng440assignment1.repository.PokemonRepository
import com.example.seng440assignment1.util.Constants.TOTAL_POKEMON
import com.example.seng440assignment1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GuessPokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val random = Random(seed = System.currentTimeMillis())
    var randomPokemon = mutableStateOf(
        PokedexListEntry(
            "Loading",
            "https://upload.wikimedia.org/wikipedia/commons/8/89/HD_transparent_picture.png",
            0
        )
    )

    var blendMode = mutableStateOf(BlendMode.SrcIn)

    private var isLoading = mutableStateOf(false)

    var searchText = mutableStateOf("")

    var generation = mutableStateOf("")

    init {
        getRandomPokemon("")
    }

    fun getRandomPokemon(
        selectedText: String
    ) {
        viewModelScope.launch {
            isLoading.value = true
            val generationNumber = selectedText.lastOrNull().toString()
            var first = 1
            var last = TOTAL_POKEMON + 1
            if (generationNumber == "1") {
                first = 1
                last = 152
            }
            if (generationNumber == "2") {
                first = 152
                last = 252
            }
            if (generationNumber == "3") {
                first = 252
                last = 387
            }
            if (generationNumber == "4") {
                first = 387
                last = 494
            }
            if (generationNumber == "5") {
                first = 494
                last = 650
            }
            if (generationNumber == "6") {
                first = 650
                last = 722
            }
            if (generationNumber == "7") {
                first = 722
                last = 810
            }
            if (generationNumber == "8") {
                first = 810
                last = TOTAL_POKEMON + 1
            }
            when(val result = repository.getPokemonInfo(random.nextInt(first, last).toString())) {
                is Resource.Success -> {
                    val pokedexEntry = result.data!!
                    val pokemon = PokedexListEntry(
                        pokedexEntry.name.replaceFirstChar(Char::titlecase),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokedexEntry.id}.png",
                        pokedexEntry.id
                    )
                    isLoading.value = false
                    blendMode.value = BlendMode.SrcIn
                    randomPokemon.value = pokemon
                }
                else -> {}
            }
        }
    }

    fun revealPokemonColour() {
        viewModelScope.launch {
            blendMode.value = BlendMode.Dst
            delay(2500)
            getRandomPokemon(generation.value)
        }
    }

}