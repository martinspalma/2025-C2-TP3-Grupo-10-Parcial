package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.MainActivity
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily


@Composable
fun FingerprintScreen(navController: NavHostController) {


    val STANDARD_HEADER_HEIGHT = 140.dp
    var selectedIndex by remember { mutableIntStateOf(4) }
    val ICON_BACKGROUND_COLOR = Color(0xFFE3F2FD) // Fondo claro del círculo

    //bloque INTENT
    val NAV_DESTINATION_KEY = "startDestination"
    val context = LocalContext.current
    val activity = context as? Activity
    // --- FUNCIÓN AUXILIAR: Navegar de vuelta a MainActivity ---
    fun navigateBackToMain(route: String) {
        val NAV_DESTINATION_KEY = "startDestination"

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            putExtra(NAV_DESTINATION_KEY, route)
        }
        context.startActivity(intent)
        // Opcional, pero recomendado para cerrar el Activity vieja inmediatamente
        (context as? Activity)?.finish()
    }


    @Composable
    fun FingerprintItem(labelResId: Int, iconResId: Int, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 1. Contenedor de Ícono (Círculo)
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(ICON_BACKGROUND_COLOR, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = stringResource(labelResId),
                        // Ícono ocupando la mayor parte del Box
                        modifier = Modifier.fillMaxSize(0.99f)
                    )
                }

                Spacer(Modifier.width(16.dp))

                // 2. Texto de la Opción
                Text(
                    text = stringResource(labelResId),
                    color = LettersAndIcons,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
    // ----------------------------------------------------------------------

    AppScreenShell(
        screenTitle = stringResource(R.string.fingerprint_list_title),
        headerHeight = STANDARD_HEADER_HEIGHT,
        navController = navController,
        bottomBar = {
            BottomNavBar(
                selected = selectedIndex,
                onSelect = { index ->
                    selectedIndex = index // Actualiza el estado visual
                    when (index) {
                        0 -> navigateBackToMain("home") // <-- NAVEGACIÓN CORREGIDA
                        2 -> navigateBackToMain("transactions") // <-- NAVEGACIÓN CORREGIDA
                        3 -> navigateBackToMain("categories") // <-- NAVEGACIÓN CORREGIDA
                        4 -> { /* Ya estamos en Profile (no hacemos nada, solo actualizamos el índice) */ }
                        else -> Unit
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Spacer(Modifier.width(32.dp))
            Spacer(Modifier.height(40.dp))
            // --- LISTA DE OPCIONES DE HUELLA (USO DE NAVEGACIÓN DIRECTA) ---
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp)
            ) {
                // 1. HUELLA EXISTENTE
                FingerprintItem(
                    labelResId = R.string.fingerprint_john_label,
                    iconResId = R.drawable.ic_fingerprint_blue,
                    onClick = { navController.navigate("use_fingerprint") }
                )

                // 2. AÑADIR HUELLA
                FingerprintItem(
                    labelResId = R.string.fingerprint_add_label,
                    iconResId = R.drawable.ic_add_blue,
                    onClick = { navController.navigate("fingerprint_setup") }
                )
            }

            Spacer(Modifier.weight(1f))
        }
    }
}