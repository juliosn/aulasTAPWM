package com.example.pokemonroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemon.ui.PokemonViewModelFactory
import com.example.pokemonroom.ui.PokemonScreen
import com.example.pokemonroom.data.PokemonRepository
import com.example.pokemonroom.ui.PokemonViewModel

@Composable
fun PokemonNavGraph(navController: NavHostController, pokemonRepository: PokemonRepository) {
    val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModelFactory(pokemonRepository))

    NavHost(navController, startDestination = "pokemonScreen") {
        composable("pokemonScreen") { PokemonScreen(viewModel) }
    }
}
