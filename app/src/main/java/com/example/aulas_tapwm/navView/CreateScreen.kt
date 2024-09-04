package com.example.aulas_tapwm.navView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aulas_tapwm.roomDB.Pessoa
import com.example.aulas_tapwm.viewModel.PessoaViewModel

@Composable
fun CreateScreen(navController: NavController, viewModel: PessoaViewModel) {
    val customColor = Color(0xFFE2E7C4)

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    val pessoa = Pessoa(nome = nome, telefone = telefone)

    Column(
        modifier = Modifier
            .background(customColor)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Cadastrar Pessoa",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (nome.isNotBlank() && telefone.isNotBlank()) {
                    viewModel.upsertPessoa(pessoa)
                    nome = ""
                    telefone = ""
                    navController.navigate("read")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cadastrar")
        }
    }
}
