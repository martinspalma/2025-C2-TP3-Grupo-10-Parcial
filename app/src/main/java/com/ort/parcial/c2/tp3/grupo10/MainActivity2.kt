package com.ort.parcial.c2.tp3.grupo10

// 1. ELIMINA esta línea. Es la causa del crash.
// import android.R

// 2. AÑADE el import correcto a los recursos de TU proyecto.
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
                        // 3. Ahora esto buscará en tu carpeta res/drawable
                        // Asegúrate de tener un recurso llamado 'ic_perfil' o el que quieras usar.
                        painter = painterResource(id = R.drawable.ic_perfil),
                        contentDescription = "Icono de perfil"
                    )
                    Button(onClick = {
                        finish() // Esto está perfecto, cierra esta actividad y vuelve a MainActivity
                    }) {
                        Text("Volver")
                    }
                }
            }
        }
    }
}
