package com.ort.parcial.c2.tp3.grupo10.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InputTextPhone(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "11 2222 3333",
    modifier: Modifier = Modifier,
    focusedBorderColor: Color,
    unfocusedBorderColor: Color,
    focusedContainerColor: Color,
    unfocusedContainerColor: Color,
    cursorColor: Color,
    textColor: Color = focusedBorderColor
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val filtrado = input.filter { it.isDigit() }

            if (filtrado.length <= 10) {
                onValueChange(filtrado)
            }
        },
        textStyle = LocalTextStyle.current.copy(color = textColor),
        placeholder = { Text(placeholder, color = textColor.copy(alpha = 0.6f)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            cursorColor = cursorColor
        )
    )
}