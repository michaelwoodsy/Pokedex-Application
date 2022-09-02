package com.example.seng440assignment1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.seng440assignment1.data.remote.responses.Pokemon
import com.example.seng440assignment1.repository.PokemonRepository
import com.example.seng440assignment1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }


}