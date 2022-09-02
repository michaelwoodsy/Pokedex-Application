package com.example.seng440assignment1.repository

import com.example.seng440assignment1.data.remote.PokeApi
import com.example.seng440assignment1.data.remote.responses.Pokemon
import com.example.seng440assignment1.data.remote.responses.PokemonList
import com.example.seng440assignment1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
){

    suspend fun getPokemonList(limit: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }


    suspend fun getPokemonInfo(pokemon: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemon)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }
}