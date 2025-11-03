package com.ort.parcial.c2.tp3.grupo10.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // 🔹 Control de opacidad para el fade-in
    val alpha = remember { Animatable(0f) }

    // 🔹 Animación de entrada + espera + salida
    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200)
        )
        delay(1800) // tiempo visible antes de ir a la siguiente pantalla
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true } // elimina splash del backstack
        }
    }

    // 🔹 Contenido del splash
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
            .alpha(alpha.value),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícono negro
            Image(
                painter = painterResource(id = R.drawable.icononegro), // tu ícono SVG
                contentDescription = "FinWise Icon",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Texto FinWise en blanco
            Image(
                painter = painterResource(id = R.drawable.finwise_blanco), // tu texto SVG blanco
                contentDescription = "FinWise Logo",
                modifier = Modifier.height(40.dp)
            )
        }
    }
}
