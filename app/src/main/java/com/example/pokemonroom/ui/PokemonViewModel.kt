package com.example.pokemonroom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonroom.data.Pokemon
import com.example.pokemonroom.data.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    val pokemonList: Flow<List<Pokemon>> = repository.getAllPokemon()

    fun getPokemonById(id: Int): Flow<Pokemon> = repository.getPokemonById(id)

    fun addOrUpdatePokemon(id: Int? = null, name: String, type: String, level: Int) {
        val pokemon = Pokemon(id = id ?: 0, name = name, type = type, level = level)
        viewModelScope.launch {
            repository.insertPokemon(pokemon)
        }
    }

    fun deletePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            repository.deletePokemon(pokemon)
        }
    }
}
