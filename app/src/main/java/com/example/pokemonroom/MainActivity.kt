package com.example.pokemonroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pokemonroom.data.AppContainer
import com.example.pokemonroom.ui.navigation.PokemonNavGraph
import com.example.pokemonroom.ui.theme.PokemonRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonRoomTheme{
                val appContainer = AppContainer(applicationContext)
                val pokemonRepository = appContainer.pokemonRepository
                val navController = rememberNavController()
                PokemonNavGraph(navController = navController, pokemonRepository = pokemonRepository)}
        }
    }
}
