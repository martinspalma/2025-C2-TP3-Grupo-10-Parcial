package com.ort.parcial.c2.tp3.grupo10.ui.screens.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew2
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily

@Composable
fun SecurityPinScreen(
    navController: NavController,
    onAccept: (String) -> Unit = { navController.navigate("new_password") },
    onResend: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))
            Text(
                text = "Security Pin",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 50.dp))
                    .background(BackgroundGreenWhiteAndLetters)
                    .padding(horizontal = 32.dp, vertical = 48.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Enter Security Pin",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.DarkGray,
                        fontFamily = PoppinsFamily
                    )

                    Spacer(Modifier.height(24.dp))

                    // Invisible text field to capture the 6 digits and a row showing the bubbles
                    OtpInput(
                        length = 6,
                        value = pin,
                        onValueChange = { new ->
                            val digits = new.filter { it.isDigit() }.take(6)
                            pin = digits
                        }
                    )

                    Spacer(Modifier.height(32.dp))

                    Button(
                        onClick = { if (pin.length == 6) onAccept(pin) },
                        enabled = pin.length == 6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MainGreen)
                    ) {
                        Text("Accept", color = Color.White)
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onResend,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Honeydew,
                            contentColor = MainGreen
                        )
                    ) {
                        Text("Send Again")
                    }
                }
            }
        }
    }
}

@Composable
private fun OtpInput(
    length: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    // Hidden field that receives input when tapping the row
    var focused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focused) {
        if (focused) focusRequester.requestFocus()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .clickable { focused = true }
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                repeat(length) { idx ->
                    val char = value.getOrNull(idx)?.toString() ?: ""
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .border(2.dp, Honeydew2, CircleShape)
                            .background(Color.Transparent, CircleShape)
                    ) {
                        Text(text = char, color = Honeydew2, fontSize = 18.sp)
                    }
                }
            }
        }

        // Invisible text field capturing the digits
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            modifier = Modifier
                .padding(top = 1.dp)
                .size(1.dp) // effectively hidden but focusable
                .focusRequester(focusRequester)
        )
    }
}
