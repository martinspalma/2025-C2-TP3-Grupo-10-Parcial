package com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.DarkModeGreenBar
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LeagueSpartanFamily
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppButton
import com.ort.parcial.c2.tp3.grupo10.ui.components.SocialAuthButton

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {

    val emailState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    ) {
        // --- 1. HEADER (Título superior) ---
        Text(
            text = stringResource(R.string.forgot_password),
            color = LettersAndIcons,
            fontSize = 30.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 32.dp, top = 80.dp)
        )

        // --- 2. CUERPO / TARJETA (Fondo de color F1FFF3) ---
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp),
            color = BackgroundGreenWhiteAndLetters,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(40.dp))

                // Título de la tarjeta
                Text(
                    text = stringResource(R.string.reset_password),
                    color = DarkModeGreenBar,
                    fontFamily = PoppinsFamily,
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(8.dp))

                // Descripción
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    color = DarkModeGreenBar.copy(alpha = 0.6f),
                    fontFamily = LeagueSpartanFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(32.dp))

                // Etiqueta del Input
                Text(
                    text = stringResource(R.string.enter_email_address),
                    color = LettersAndIcons,
                    fontWeight = FontWeight.Medium,
                    fontFamily = PoppinsFamily,
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(8.dp))

                // Campo de Texto (Input)
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    placeholder = {
                        Text(
                            stringResource(R.string.email_placeholder),
                            // --- ESTILOS DE TIPOGRAFÍA DE FIGMA ---
                            fontFamily = PoppinsFamily,    // Poppins
                            fontWeight = FontWeight.Normal, // 400 / Regular
                            fontSize = 16.sp,               // 16px
                            lineHeight = 14.sp) // <--- Ya NO se pasa el color aquí
                    },
                    shape = RoundedCornerShape(50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = LettersAndIcons,
                        unfocusedTextColor = LettersAndIcons,
                        unfocusedPlaceholderColor = LettersAndIcons.copy(alpha = 0.45f),
                        focusedPlaceholderColor = LettersAndIcons.copy(alpha = 0.45f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )

                Spacer(Modifier.height(32.dp))

                // --- BOTÓN NEXT STEP (Principal) ---
                AppButton(
                    text = stringResource(R.string.next_step_button),
                    onClick = { navController.navigate("securityPin") },

                    buttonWidth = 169.dp,
                    buttonHeight = 32.dp,
                    containerColor = MainGreen,
                    textColor = LettersAndIcons
                )

                Spacer(Modifier.height(64.dp))

// --- BOTÓN SIGN UP (Secundario) ---
                AppButton(
                    text = stringResource(R.string.sign_up_button),
                    onClick = { /* Acción de registro */ },
                    // Usa las mismas dimensiones
                    buttonWidth = 169.dp,
                    buttonHeight = 32.dp,
                    containerColor = Color.White,
                    textColor = MainGreen, // El texto es MainGreen
                    borderColor = MainGreen // <-- Le pasa el borde
                )

                Spacer(Modifier.height(16.dp))

                // Texto "o regístrate con"
                Text(
                    text = stringResource(R.string.or_sign_up_with),
                    color = LettersAndIcons.copy(alpha = 0.5f),
                    fontFamily = LeagueSpartanFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    lineHeight = 15.sp
                )

                Spacer(Modifier.height(16.dp))

                // --- ICONOS DE REDES SOCIALES (Placeholders) ---
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 1. Botón de Facebook (33dp)
                    SocialAuthButton(
                        iconId = R.drawable.ic_facebook, // <-- Asume que importaste el logo aquí
                        contentDesc = stringResource(R.string.login_with_facebook),
                        onClick = { /* Acción de login con Facebook */ },
                        size = 33.dp // Tamaño exacto de Figma
                    )

                    // 2. Botón de Google (33dp)
                    SocialAuthButton(
                        iconId = R.drawable.ic_google, // <-- Asume que importaste el logo aquí
                        contentDesc = stringResource(R.string.login_with_google),
                        onClick = { /* Acción de login con Google */ },
                        size = 33.dp
                    )
                }

                Spacer(Modifier.height(32.dp))

                // Texto "Don't have an account?"
                Row (
                    modifier = Modifier.width(273.dp)
                    ) {
                    Text(
                        text = stringResource(R.string.dont_have_account),
                        color = LettersAndIcons.copy(alpha = 0.5f),
                        fontFamily = LeagueSpartanFamily,    // League Spartan
                        fontWeight = FontWeight.Light,      // 300 / Light
                        fontSize = 13.sp,                   // 13px
                        lineHeight = 15.sp
                    )
                    Text(
                        text = stringResource(R.string.sign_up_link),
                        color = MainGreen,
                        fontFamily = LeagueSpartanFamily,    // League Spartan
                        fontWeight = FontWeight.Light,      // 300 / Light
                        fontSize = 13.sp,                   // 13px
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}