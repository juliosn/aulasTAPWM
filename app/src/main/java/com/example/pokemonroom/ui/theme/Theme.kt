package com.example.pokemonroom.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// Cores personalizadas do tema Pokémon
val pokeballRed = Color(0xFFFF0000)
val pokeballWhite = Color(0xFFFFFFFF)
val pikachuYellow = Color(0xFFF4D03F)
val backgroundGray = Color.LightGray

private val DarkColorScheme = darkColorScheme(
    primary = pokeballRed,
    onPrimary = pokeballWhite,
    secondary = pikachuYellow,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.DarkGray,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = pokeballRed,
    onPrimary = pokeballWhite,
    secondary = pikachuYellow,
    background = backgroundGray,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)



@Composable
fun PokemonRoomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,  // Você pode ajustar a tipografia conforme necessário
        content = content
    )
}
