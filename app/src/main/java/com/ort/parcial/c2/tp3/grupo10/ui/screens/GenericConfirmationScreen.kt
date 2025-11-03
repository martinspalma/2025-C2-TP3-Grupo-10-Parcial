package com.ort.parcial.c2.tp3.grupo10.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import androidx.compose.runtime.LaunchedEffect
//import kotlinx.coroutines.delay



@Composable
fun GenericConfirmationScreen(
    navController: NavHostController,
    message: String,
    destinationRoute: String
) {

    //TEMPORIZADOR DE NAVEGACIÓN
/*
    LaunchedEffect(Unit) {
        delay(2500)
        navController.navigate(destinationRoute) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Tu icono de éxito personalizado
            Image(
                painter = painterResource(id = R.drawable.ic_success_circle),
                contentDescription = stringResource(R.string.success_icon_description),
                modifier = Modifier.size(120.dp),
            )

            Spacer(Modifier.height(40.dp))

            Text(
                text = message, // <-- Usa el mensaje pasado por parámetro
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}
