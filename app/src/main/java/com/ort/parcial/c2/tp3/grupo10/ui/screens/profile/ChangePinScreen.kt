package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell

// Nota: Deberías añadir este string a tu strings.xml si no existe
// <string name="change_pin_title">Change PIN</string>

@Composable
fun ChangePinScreen(navController: NavHostController) {

    // El título de la pantalla
    val screenTitle = "Change PIN" // o stringResource(R.string.change_pin_title)

    // Usamos el Shell estándar de la aplicación para mantener la consistencia
    AppScreenShell(
        screenTitle = screenTitle,
        navController = navController
    ) { padding ->
        // Contenido simple de la pantalla
        Text(
            // Mostramos el nombre de la clase y el parámetro para confirmación
            text = "ChangePinScreen(navController: NavHostController)"
        )
    }
}