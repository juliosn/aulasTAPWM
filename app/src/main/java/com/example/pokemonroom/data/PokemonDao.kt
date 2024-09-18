package com.example.pokemonroom.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id: Int): Flow<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon)

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)
}
