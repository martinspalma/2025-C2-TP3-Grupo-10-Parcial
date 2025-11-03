package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell

// Nota: Deberías añadir este string a tu strings.xml si no existe
// <string name="edit_profile_title">Edit Profile</string>

@Composable
fun EditProfileScreen(navController: NavHostController) {

    // El título de la pantalla
    val screenTitle = "Edit Profile" // o stringResource(R.string.edit_profile_title)

    // Usamos el Shell estándar de la aplicación para mantener la consistencia
    AppScreenShell(
        screenTitle = screenTitle,
        navController = navController
    ) { padding ->
        // Contenido simple de la pantalla
        Text(
            // Mostramos el nombre de la clase y el parámetro para confirmación
            text = "EditProfileScreen(navController: NavHostController)"
        )
    }
}
