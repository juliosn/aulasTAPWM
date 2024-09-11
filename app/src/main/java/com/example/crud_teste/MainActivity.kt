package com.example.crud_teste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.crud_teste.repository.PessoaRepository
import com.example.crud_teste.ui.screen.CadastroScreen
import com.example.crud_teste.ui.screen.ConsultaScreen
import com.example.crud_teste.ui.theme.Crud_testeTheme
import com.example.crud_teste.viewmodel.PessoaViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }

    private val pessoaViewModel: PessoaViewModel by viewModels {
        PessoaViewModel.Factory(PessoaRepository(db))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Crud_testeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(pessoaViewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(pessoaViewModel: PessoaViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "cadastro") {
        composable("cadastro") {
            CadastroScreen(pessoaViewModel, navController)
        }
        composable("consulta") {
            ConsultaScreen(pessoaViewModel, navController)
        }
    }
}
