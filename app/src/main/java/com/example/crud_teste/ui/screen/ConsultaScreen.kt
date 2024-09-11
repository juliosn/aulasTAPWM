package com.example.crud_teste.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crud_teste.viewmodel.PessoaViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.crud_teste.model.Pessoa
import com.example.crud_teste.ui.screen.components.PessoaItem

@Composable
fun ConsultaScreen(pessoaViewModel: PessoaViewModel, navController: NavController) {
    val pessoas = remember { mutableStateListOf<Pair<String, Pessoa>>() }

    // Carregar pessoas quando a tela for carregada
    LaunchedEffect(Unit) {
        pessoaViewModel.getAllPessoas(
            onSuccess = { lista ->
                pessoas.clear()
                pessoas.addAll(lista)
            },
            onFailure = { exception ->
                // Tratar erro
            }
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pessoas Cadastradas",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(pessoas.size) { index ->
                val (id, pessoa) = pessoas[index]
                PessoaItem(
                    pessoa = pessoa,
                    onDelete = {
                        pessoaViewModel.deletePessoa(id, onSuccess = {
                            // Remove a pessoa da lista local
                            pessoas.removeAt(index)
                        }, onFailure = {
                            // Tratar falha
                        })
                    },
                    onEdit = { nomeEditado, telefoneEditado ->
                        val pessoaAtualizada = Pessoa(nome = nomeEditado, telefone = telefoneEditado)
                        pessoaViewModel.updatePessoa(id, pessoaAtualizada, onSuccess = {
                            // Atualiza os dados localmente
                            pessoas[index] = id to pessoaAtualizada
                        }, onFailure = {
                            // Tratar falha
                        })
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar à tela de cadastro
        Button(onClick = {
            navController.navigate("cadastro")
        }) {
            Text("Voltar para Cadastro")
        }
    }
}
