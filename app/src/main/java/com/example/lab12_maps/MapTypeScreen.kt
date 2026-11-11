package com.example.lab12_maps

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapTypeScreen() {
    val misti = LatLng(-16.2944, -71.4093)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-16.36, -71.50), 11f)
    }

    var tipoMapa by remember { mutableStateOf(MapType.NORMAL) }

    val tipos = listOf(
        MapType.NORMAL to "Normal",
        MapType.SATELLITE to "Satélite",
        MapType.HYBRID to "Híbrido",
        MapType.TERRAIN to "Terreno"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = tipoMapa)
        ) {
            Marker(
                state = rememberMarkerState(position = misti),
                title = "Volcán Misti",
                snippet = "Prueba el modo Terreno"
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .statusBarsPadding()
                .horizontalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tipos.forEach { (tipo, nombre) ->
                val seleccionado = tipo == tipoMapa
                Button(
                    onClick = { tipoMapa = tipo },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (seleccionado) MaterialTheme.colorScheme.primary else Color.White,
                        contentColor = if (seleccionado) Color.White else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(nombre)
                }
            }
        }
    }
}
