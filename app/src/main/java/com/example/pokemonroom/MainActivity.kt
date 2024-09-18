package com.example.pokemonroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokemonroom.data.AppContainer
import com.example.pokemon.ui.PokemonViewModelFactory
import com.example.pokemonroom.ui.PokemonScreen
import com.example.pokemonroom.ui.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer(applicationContext)
        val pokemonRepository = appContainer.pokemonRepository

        setContent {
            val viewModel: PokemonViewModel = viewModel(
                factory = PokemonViewModelFactory(pokemonRepository)
            )
            PokemonScreen(viewModel)
        }
    }
}
