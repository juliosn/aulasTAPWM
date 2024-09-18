package com.example.pokemonroom.data

import android.content.Context
import androidx.room.Room

class AppContainer(context: Context) {
    private val database: PokedexDatabase by lazy {
        Room.databaseBuilder(context, PokedexDatabase::class.java, "pokedex_db").build()
    }

    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(database.pokemonDao())
    }
}


