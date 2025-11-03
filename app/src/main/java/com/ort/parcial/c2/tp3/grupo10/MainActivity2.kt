package com.ort.parcial.c2.tp3.grupo10

// 1. ELIMINA esta línea
// import android.R

// 2. AÑADE la importación correcta de tu proyecto
import com.ort.parcial.c2.tp3.grupo10.R

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource


class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Estás en Activity 2")
                    Icon(
                        // 3. Usa tu R local para encontrar el recurso
                        painter = painterResource(id = R.drawable.ic_perfil), // <-- Cambiado a un icono que probablemente exista en tu proyecto
                        contentDescription = "Icono de perfil"
                    )
                    Button(onClick = {
                        finish() // 👈 Esto es correcto, cierra esta actividad y vuelve a MainActivity
                    }) {
                        Text("Volver")
                    }
                }
            }
        }
    }
}
