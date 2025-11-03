package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource



val ICON_CONTAINER_WIDTH = 57.dp
val ICON_CONTAINER_HEIGHT = 53.dp

@Composable
fun ProfileOptionItem(
    label: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    // La altura que pasaremos desde la pantalla Profile (ej: 53dp o 28dp)
    itemHeight: Dp = 80.dp
) {
    Column(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight) // Aplicamos la altura del ítem (ej: 53dp
                .padding(start = 0.dp, end = 0.dp), // Quitamos el padding vertical para que la altura sea exacta
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                // 1. CONTENEDOR DE ÍCONO AZUL (57dp x 57dp)
                Box(
                    modifier = Modifier
                        .width(ICON_CONTAINER_WIDTH) // <-- Ancho 57dp
                        .height(ICON_CONTAINER_HEIGHT)
                        .background(Color.White, CircleShape)
                        .padding(0.dp), // Padding interno para que el ícono no toque el borde
                    contentAlignment = Alignment.Center
                ) {
                    // USAMOS LA IMAGEN DE RECURSO EN LUGAR DE IMAGEN VECTORIAL
                    Image(
                        painter = painterResource(id = iconRes), // <-- CARGA EL ÍCONO DE FIGMA
                        contentDescription = label,
                        modifier = Modifier.fillMaxSize(0.99f),

                    )
                }

                Spacer(Modifier.width(13.dp))

                // 2. Texto de la Opción (TIPOGRAFÍA EXACTA)
                Text(
                    text = label,
                    color = LettersAndIcons,
                    // --- ESTILOS DE FIGMA ---
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Medium, // 500 / Medium
                    fontSize = 15.sp, // 15px
                    lineHeight = 15.sp // 100% line-height (para 15px)

                )
            }
        }

    }
}