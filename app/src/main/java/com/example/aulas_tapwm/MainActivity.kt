package com.example.aulas_tapwm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.aulas_tapwm.navView.CreateScreen
import com.example.aulas_tapwm.navView.EditScreen
import com.example.aulas_tapwm.navView.ReadScreen
import com.example.aulas_tapwm.roomDB.PessoaDataBase
import com.example.aulas_tapwm.ui.theme.Aulas_TAPWMTheme
import com.example.aulas_tapwm.viewModel.PessoaViewModel
import com.example.aulas_tapwm.viewModel.Repository

class MainActivity : ComponentActivity() {
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }

    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>): T{
                    return PessoaViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aulas_TAPWMTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "create") {
                    composable("create") {
                        CreateScreen(navController, viewModel)
                    }
                    composable("read") {
                        ReadScreen(navController, viewModel)
                    }
                    composable(
                        "edit/{pessoaId}",
                        arguments = listOf(navArgument("pessoaId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val pessoaId = backStackEntry.arguments?.getInt("pessoaId") ?: 0
                        EditScreen(navController, viewModel, pessoaId)
                    }
                }
            }
        }
    }
}
