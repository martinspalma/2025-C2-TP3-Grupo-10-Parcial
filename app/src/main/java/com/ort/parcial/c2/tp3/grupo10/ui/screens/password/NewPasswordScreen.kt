package com.ort.parcial.c2.tp3.grupo10.ui.screens.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew2
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily

@Composable
fun NewPasswordScreen(
    navController: NavController,
    onChanged: () -> Unit = { navController.navigate("password_changed") }
) {
    var pass1 by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var show1 by remember { mutableStateOf(false) }
    var show2 by remember { mutableStateOf(false) }

    val valid = pass1.isNotEmpty() && pass1 == pass2 && pass1.length >= 6

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))
            Text(
                text = "New Password",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGreenWhiteAndLetters, RoundedCornerShape(topStart = 40.dp, topEnd = 50.dp))
                    .padding(horizontal = 32.dp, vertical = 48.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "New Password",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = pass1,
                        onValueChange = { pass1 = it },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Honeydew2),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(50),
                        visualTransformation = if (show1) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { show1 = !show1 }) {
                                Icon(if (show1) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    )

                    Spacer(Modifier.height(18.dp))

                    Text(
                        text = "Confirm New Password",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = pass2,
                        onValueChange = { pass2 = it },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Honeydew2),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(50),
                        visualTransformation = if (show2) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { show2 = !show2 }) {
                                Icon(if (show2) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    )

                    if (pass2.isNotEmpty() && pass1 != pass2) {
                        Spacer(Modifier.height(8.dp))
                        Text(text = "Passwords do not match", color = Color(0xFFD32F2F))
                    }

                    Spacer(Modifier.height(32.dp))

                    Button(
                        onClick = onChanged,
                        enabled = valid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MainGreen)
                    ) {
                        Text("Change Password", color = Color.White)
                    }
                }
            }
        }
    }
}
