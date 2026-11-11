package com.example.lab12_maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab12_maps.ui.theme.Lab12_MapsTheme

enum class Pantalla { MENU, GUIADA, TIPOS_MAPA, UBICACION }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab12_MapsTheme {
                var pantalla by rememberSaveable { mutableStateOf(Pantalla.MENU) }

                BackHandler(enabled = pantalla != Pantalla.MENU) {
                    pantalla = Pantalla.MENU
                }

                when (pantalla) {
                    Pantalla.MENU -> MenuScreen(onSeleccionar = { pantalla = it })
                    Pantalla.GUIADA -> MapScreen()
                    Pantalla.TIPOS_MAPA -> MapTypeScreen()
                    Pantalla.UBICACION -> LocationScreen()
                }
            }
        }
    }
}

@Composable
fun MenuScreen(onSeleccionar: (Pantalla) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Laboratorio 12: Google Maps",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { onSeleccionar(Pantalla.GUIADA) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Parte guiada")
            }
            Button(
                onClick = { onSeleccionar(Pantalla.TIPOS_MAPA) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ejercicio 1: Tipos de mapa")
            }
            Button(
                onClick = { onSeleccionar(Pantalla.UBICACION) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ejercicio 2: Mi ubicación")
            }
        }
    }
}
