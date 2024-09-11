package com.example.crud_teste.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import com.example.crud_teste.model.Pessoa

@Composable
fun PessoaItem(
    pessoa: Pessoa,
    onDelete: () -> Unit,
    onEdit: (String, String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var nome by remember { mutableStateOf(pessoa.nome) }
    var telefone by remember { mutableStateOf(pessoa.telefone) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        if (isEditing) {
            // Campos de edição
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") }
            )
            TextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text("Telefone") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                // Botão para salvar a edição
                Button(onClick = {
                    onEdit(nome, telefone)
                    isEditing = false
                }) {
                    Text("Salvar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botão para cancelar a edição
                Button(onClick = {
                    isEditing = false
                    nome = pessoa.nome
                    telefone = pessoa.telefone
                }) {
                    Text("Cancelar")
                }
            }

        } else {
            // Exibir dados da pessoa
            Text("Nome: ${pessoa.nome}")
            Text("Telefone: ${pessoa.telefone}")

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                // Botão para editar
                Button(onClick = { isEditing = true }) {
                    Text("Editar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botão para deletar
                Button(onClick = onDelete) {
                    Text("Excluir")
                }
            }
        }
    }
}
