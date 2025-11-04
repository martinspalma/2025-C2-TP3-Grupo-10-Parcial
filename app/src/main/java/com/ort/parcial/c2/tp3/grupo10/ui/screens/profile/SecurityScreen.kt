package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.MainActivity
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar

@Composable
fun SecurityScreen(navController: NavHostController) {

    val STANDARD_HEADER_HEIGHT = 140.dp
    var selectedIndex by remember { mutableIntStateOf(4) }

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

    AppScreenShell(
        screenTitle = stringResource(R.string.security_title),
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

            // --- TÍTULO DE SECCIÓN "Security" (Estilo Figma) ---
            Text(
                text = stringResource(R.string.security_title),
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp, bottom = 10.dp)
            )


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clickable(onClick = { navController.navigate("change_pin") }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.option_change_pin),
                        color = LettersAndIcons,
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                // 2. FINGERPRINT
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        // NAVEGACIÓN DIRECTA
                        .clickable(onClick = { navController.navigate("fingerprint_list") }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.option_fingerprint),
                        color = LettersAndIcons,
                        // Estilos Figma: Medium 15sp
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clickable(onClick = { navController.navigate("terms_and_conditions") }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.option_terms_and_conditions),
                        color = LettersAndIcons,
                        // Estilos Figma: Medium 15sp
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

            Spacer(Modifier.weight(1f))
        }
    }
}