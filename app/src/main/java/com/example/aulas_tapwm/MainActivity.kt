package com.example.aulas_tapwm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulas_tapwm.ui.theme.Aulas_TAPWMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val customColor = Color(0xFFE2E7C4)

    Column(Modifier.background(customColor)) {
        Row(Modifier.padding(20.dp)) {

        }
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(
                text = "App Database",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Row(Modifier.padding(20.dp)) {}
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            TextField(
                value = "",
                onValueChange = {},
                label = {Text("Nome:")}
            )
        }
        Row(Modifier.padding(20.dp)) {}
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            TextField(
                value = "",
                onValueChange = {},
                label = {Text("Telefone:")}
            )
        }
        Row(Modifier.padding(20.dp)) {}
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Button(onClick = { /*TODO*/

            }) {
                Text("Cadastrar")
            }
            Row(Modifier.padding(20.dp)) {}
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    Aulas_TAPWMTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            App()
        }
    }
}