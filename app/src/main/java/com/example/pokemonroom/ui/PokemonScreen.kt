package com.example.pokemonroom.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pokemonroom.R
import com.example.pokemonroom.data.Pokemon
import com.example.pokemonroom.ui.theme.pokeballRed
import com.example.pokemonroom.ui.theme.pokeballWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(viewModel: PokemonViewModel) {
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }

    val pokemonList by viewModel.pokemonList.collectAsState(initial = emptyList())

    // Verifica se todos os campos estão preenchidos
    val isFormValid = name.isNotBlank() && type.isNotBlank() && level.isNotBlank()

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.bg_pokemon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Linha com ícones e texto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_bulba),
                    contentDescription = "Ícone Bulbasaur",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (selectedPokemon == null) "Adicionar Pokémon" else "Atualizar Pokémon",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ico_pika),
                    contentDescription = "Ícone Pikachu",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Campo de entrada Nome
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(pokeballWhite, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de entrada Tipo
            TextField(
                value = type,
                onValueChange = { type = it },
                label = { Text("Tipo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(pokeballWhite, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de entrada Nível
            TextField(
                value = level,
                onValueChange = { level = it },
                label = { Text("Nível") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(pokeballWhite, shape = RoundedCornerShape(8.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )

            // Botão de adicionar/atualizar Pokémon
            Button(
                onClick = {
                    if (isFormValid) {
                        viewModel.addOrUpdatePokemon(selectedPokemon?.id, name, type, level.toIntOrNull() ?: 1)
                        name = ""
                        type = ""
                        level = ""
                        selectedPokemon = null
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
                enabled = isFormValid // Desabilita o botão se o formulário não for válido
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = "Pokébola",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (selectedPokemon == null) "Adicionar Pokémon" else "Atualizar Pokémon")
            }

            // Lista de Pokémon em um LazyColumn
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(pokemonList) { pokemon ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = pokeballWhite)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Nome: ${pokemon.name}",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = pokeballRed
                                )
                                Text(text = "Tipo: ${pokemon.type}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Nível: ${pokemon.level}", style = MaterialTheme.typography.bodyMedium)
                            }
                            Row {
                                IconButton(onClick = {
                                    selectedPokemon = pokemon
                                    name = pokemon.name
                                    type = pokemon.type
                                    level = pokemon.level.toString()
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }
                                IconButton(onClick = {
                                    viewModel.deletePokemon(pokemon)
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
