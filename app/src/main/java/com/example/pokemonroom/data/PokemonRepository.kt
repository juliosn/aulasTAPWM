package com.example.pokemonroom.data

import kotlinx.coroutines.flow.Flow

open class PokemonRepository(private val pokemonDao: PokemonDao) {
    fun getAllPokemon(): Flow<List<Pokemon>> = pokemonDao.getAllPokemon()

    fun getPokemonById(id: Int): Flow<Pokemon> = pokemonDao.getPokemonById(id)

    suspend fun insertPokemon(pokemon: Pokemon) = pokemonDao.insertPokemon(pokemon)

    suspend fun deletePokemon(pokemon: Pokemon) = pokemonDao.deletePokemon(pokemon)
}
