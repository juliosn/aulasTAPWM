package com.example.pokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonroom.data.PokemonRepository
import com.example.pokemonroom.ui.PokemonViewModel

// A Factory para criar PokemonViewModel com o PokemonRepository
class PokemonViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica se a classe passada é a classe do ViewModel
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
