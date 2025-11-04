package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell

// Nota: Asegúrate de tener este string en strings.xml
// <string name="use_fingerprint_title">Use Fingerprint</string>

@Composable
fun UseFingerprintScreen(navController: NavHostController) {

    // El título de la pantalla
    val screenTitle = "Use Fingerprint" // o stringResource(R.string.use_fingerprint_title)

    // Usamos el Shell estándar de la aplicación
    AppScreenShell(
        screenTitle = screenTitle,
        navController = navController
    ) { padding ->
        // Contenido simple de la pantalla
        Text(
            text = screenTitle // Muestra el texto para confirmar que la pantalla se cargó
        )
    }
}
