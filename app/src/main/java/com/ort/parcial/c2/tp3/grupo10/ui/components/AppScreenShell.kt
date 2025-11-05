package com.ort.parcial.c2.tp3.grupo10.ui.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.platform.LocalContext
import com.ort.parcial.c2.tp3.grupo10.MainActivity
import kotlin.jvm.java


val STANDARD_HEADER_HEIGHT = 180.dp

/**
 * Componente Shell para estandarizar el Layout de todas las pantallas
 * (Header Verde, Tarjeta Curva, Fondo Verde Clarito).
 */
@Composable
fun AppScreenShell(
    screenTitle: String,
    // La altura del header verde puede ser variable
    headerHeight: Dp = STANDARD_HEADER_HEIGHT,
    navController: NavHostController,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit // Recibe el contenido único del formulario/lista
) {
    val context = LocalContext.current
    val NAV_DESTINATION_KEY = "startDestination"
    val NOTIFICATION_ROUTE = "notification"
    // 1. DIBUJAMOS EL FONDO PRINCIPAL Y EL CONTENEDOR DE APILAMIENTO
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen) // <--- ESTE DIBUJA EL FONDO VERDE OSCURO (MainGreen)
    ) {
        // --- 1. ICONOS DE NAVEGACIÓN Y TÍTULO (HIJOS DIRECTOS DEL BOX) ---

        // A. FLECHA DE RETROCESO (Izquierda)
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 38.dp, top = 69.dp) // Posición ajustada
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bring_back),
                contentDescription = stringResource(R.string.back_button_desc),
                tint = Color.White,
                modifier = Modifier.size(19.dp)
            )
        }

        // B. CAMPANA DE NOTIFICACIONES (Derecha)
        IconButton(
            onClick = {
                val intent = Intent(context, MainActivity::class.java).apply {

                    // ❗ CORRECCIÓN CLAVE: Usamos NEW_TASK y CLEAR_TASK ❗
                    // Esto fuerza a Android a DESTRUIR la pila actual (incluyendo MainActivity2)
                    // y RECREAR MainActivity desde cero.
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    // 2. Pasamos la ruta de destino deseada como extra
                    putExtra(NAV_DESTINATION_KEY, NOTIFICATION_ROUTE)
                }

                context.startActivity(intent)

                // Opcional, pero recomendado: Finalizar la Activity actual (MainActivity2)
                // para que no quede en segundo plano.
                (context as? Activity)?.finish()

            },
            modifier = Modifier
                .align(Alignment.TopEnd) // <-- A la derecha
                .padding(end = 16.dp, top = 61.dp)
        ) {
            // --- INICIO DEL CONTENEDOR GEOMÉTRICO ---
            Box(
                modifier = Modifier
                    .size(30.dp) // Tamaño de la campana y su fondo
                    .background(BackgroundGreenWhiteAndLetters, CircleShape), // <-- FONDO CLARITO
                contentAlignment = Alignment.Center
            ) {
                Icon(

                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.notifications_button_desc),
                    tint = LettersAndIcons.copy(alpha = 0.55f),
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // C. TÍTULO PRINCIPAL (Centrado)
        Text(
            text = screenTitle,
            color = LettersAndIcons,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.TopCenter) // <-- Centrado
                .padding(top = 64.dp)
        )

        // --- 2. CUERPO / TARJETA CON ESQUINAS CURVAS ---
        Surface(

            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight),
            color = BackgroundGreenWhiteAndLetters,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            // 3. USAMOS SCAFFOLD PARA MANEJAR LA BARRA INFERIOR
            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0),
                bottomBar = bottomBar // <-- Inyectamos la barra
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // El contenido único de la pantalla
                    content(PaddingValues(horizontal = 32.dp, vertical = 0.dp))
                }
            }
        }
    }
}