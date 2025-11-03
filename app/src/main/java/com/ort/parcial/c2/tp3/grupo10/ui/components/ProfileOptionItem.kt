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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.res.painterResource



val ICON_CONTAINER_WIDTH = 57.dp
val ICON_CONTAINER_HEIGHT = 53.dp

@Composable
fun ProfileOptionItem(
    label: String,
    // PASO 1: Hacemos el icono opcional y le damos valor null por defecto
    @DrawableRes iconRes: Int? = null,
    onClick: () -> Unit,
    itemHeight: Dp = 80.dp
) {
    // Usamos Column para poder agregar un Divider debajo si se desea
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .padding(horizontal = 0.dp), // Mantenemos el padding en 0 para que la alineación se controle desde el contenedor
            verticalAlignment = Alignment.CenterVertically,
            // Mantenemos Arrangement.SpaceBetween para que el contenido empuje la flecha de navegación
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Contenedor principal para el Ícono y el Texto
            Row(verticalAlignment = Alignment.CenterVertically) {

                // PASO 2: Lógica Condicional: Solo dibujar el bloque del icono si se proporciona un recurso
                if (iconRes != null) {

                    // 1. CONTENEDOR DE ÍCONO AZUL (57dp x 53dp)
                    Box(
                        modifier = Modifier
                            .width(ICON_CONTAINER_WIDTH)
                            .height(ICON_CONTAINER_HEIGHT)
                            .background(Color.White, CircleShape)
                            .padding(0.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = label,
                            modifier = Modifier.fillMaxSize(0.99f),
                        )
                    }

                    Spacer(Modifier.width(13.dp))
                }

                // 2. Texto de la Opción
                Text(
                    text = label,
                    color = LettersAndIcons,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,

                )
            }

        }
    }
}