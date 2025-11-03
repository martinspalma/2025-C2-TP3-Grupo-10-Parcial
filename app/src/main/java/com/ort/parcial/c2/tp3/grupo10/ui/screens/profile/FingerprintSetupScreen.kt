package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell

// Nota: Deberías añadir este string a tu strings.xml
// <string name="fingerprint_setup_title">Fingerprint Setup</string>

@Composable
fun FingerprintSetupScreen(navController: NavHostController) {

    // El título de la pantalla
    val screenTitle = "Fingerprint Setup" // o stringResource(R.string.fingerprint_setup_title)

    // Usamos el Shell estándar de la aplicación para mantener la consistencia
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
