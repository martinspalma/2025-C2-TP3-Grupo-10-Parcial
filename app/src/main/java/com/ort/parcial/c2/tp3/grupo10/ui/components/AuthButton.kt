package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = Void,
    containerColor: Color = Caribbean
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(width = 0.dp, color = Color.Transparent),
        shape = RoundedCornerShape(50)
    ) {
        Text(text, fontFamily = PoppinsFamily, color= contentColor);
    }
}
