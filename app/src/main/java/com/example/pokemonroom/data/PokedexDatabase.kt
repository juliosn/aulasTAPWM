package com.example.pokemonroom.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
