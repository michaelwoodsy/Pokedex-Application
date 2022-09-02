package com.example.seng440assignment1.data.remote

import com.example.seng440assignment1.data.remote.responses.Pokemon
import com.example.seng440assignment1.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int
    ): PokemonList

    @GET("pokemon/{value}")
    suspend fun getPokemonInfo(
        @Path("value") value: String
    ): Pokemon
}