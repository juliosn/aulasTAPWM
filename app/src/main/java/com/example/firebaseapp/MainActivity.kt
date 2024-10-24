package com.example.firebaseapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebaseapp.ui.theme.FirebaseAppTheme
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAppTheme {
                LoginScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }
    val auth = Firebase.auth
    val context = LocalContext.current

    // Verifica se ambos os campos estão preenchidos para habilitar o botão
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()

    if (isLoggedIn) {
        AuthenticationScreen(auth) { isLoggedIn = false }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Login") })
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                        isLoggedIn = true
                                    } else {
                                        val exception = task.exception
                                        when (exception) {
                                            is FirebaseAuthInvalidCredentialsException -> {
                                                Toast.makeText(context, "Email e/ou Senha incorretos.", Toast.LENGTH_SHORT).show()
                                            }
                                            is FirebaseAuthInvalidUserException -> {
                                                Toast.makeText(context, "Usuário não encontrado.", Toast.LENGTH_SHORT).show()
                                            }
                                            else -> {
                                                Toast.makeText(context, "Erro no login: ${exception?.message}", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                }
                        },
                        enabled = isButtonEnabled, // Habilita ou desabilita o botão
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Login")
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(auth: FirebaseAuth, onLogout: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Verifica se ambos os campos estão preenchidos para habilitar o botão
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cadastro de Usuários") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                                    email = ""
                                    password = ""
                                } else {
                                    val exception = task.exception
                                    when (exception) {
                                        is FirebaseAuthWeakPasswordException -> {
                                            Toast.makeText(context, "Senha muito fraca. Escolha uma senha mais forte.", Toast.LENGTH_SHORT).show()
                                        }
                                        is FirebaseAuthInvalidCredentialsException -> {
                                            Toast.makeText(context, "Email mal formatado.", Toast.LENGTH_SHORT).show()
                                        }
                                        is FirebaseAuthUserCollisionException -> {
                                            Toast.makeText(context, "Este email já está em uso.", Toast.LENGTH_SHORT).show()
                                        }
                                        else -> {
                                            Toast.makeText(context, "Erro ao cadastrar: ${exception?.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                    },
                    enabled = isButtonEnabled, // Habilita ou desabilita o botão
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cadastrar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        auth.signOut()
                        onLogout()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Logout")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    FirebaseAppTheme {
        LoginScreen()
    }
}
