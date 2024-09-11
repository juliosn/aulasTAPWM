package com.example.crud_teste.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crud_teste.viewmodel.PessoaViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.crud_teste.model.Pessoa
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(pessoaViewModel: PessoaViewModel, navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastro de Pessoa") },
                actions = {
                    IconButton(onClick = {
                        // Navega para a tela de consulta
                        navController.navigate("consulta")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Consultar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            // Campos de entrada
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para adicionar nova pessoa
            Button(onClick = {
                val pessoa = Pessoa(nome = nome, telefone = telefone)
                pessoaViewModel.addPessoa(
                    pessoa,
                    onSuccess = {
                        // Limpa os campos após adicionar
                        nome = ""
                        telefone = ""
                    },
                    onFailure = { exception ->
                        // Tratar falha
                    }
                )
            }) {
                Text("Cadastrar")
            }
        }
    }
}
