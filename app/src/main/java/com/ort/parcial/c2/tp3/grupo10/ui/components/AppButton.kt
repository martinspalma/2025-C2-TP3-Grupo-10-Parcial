package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import androidx.compose.ui.text.TextStyle


/**
 * Componente Botón Principal Flexible.
 * Recibe altura y ancho para adaptarse a diferentes diseños (ej: 32dp o 56dp).
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    buttonWidth: Dp? = null,
    buttonHeight: Dp, // <--- Altura como parámetro
    containerColor: Color,
    textColor: Color,
    borderColor: Color? = null, // Opcional para botones con borde (ej: Sign Up)
    modifier: Modifier = Modifier,
    textStyle: TextStyle? = null
) {
    // Define el borde si se proporciona un color de borde
    val borderStroke = borderColor?.let { BorderStroke(1.dp, it) }

    // Estilo por defecto (el que usan otras pantallas)
    val defaultStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        color = textColor)

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(0.dp),
        border = borderStroke,
        
        modifier = modifier
            .then(buttonWidth?.let { Modifier.width(it) } ?: Modifier.fillMaxWidth())
            .height(buttonHeight) // Usa la altura parametrizada
    ) {
        // Tipografía de Botón (Poppins SemiBold 20.sp / 22.sp)
        Text(
            text = text,
            style = textStyle ?: defaultStyle
        )
    }
}
