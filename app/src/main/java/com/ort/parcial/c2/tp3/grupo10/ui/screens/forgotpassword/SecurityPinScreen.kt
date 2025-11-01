package com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*
import com.ort.parcial.c2.tp3.grupo10.ui.components.SocialAuthButton

@Composable
fun SecurityPinScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    ) {
        //  HEADER (Título principal) ---
        Text(
            text = stringResource(R.string.security_pin_title),
            color = LettersAndIcons,
            fontSize = 30.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        )

        //  CUERPO ---
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
                    text = stringResource(R.string.enter_security_pin_label),
                    color = DarkModeGreenBar,
                    fontFamily = PoppinsFamily,
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(40.dp))

                // --- CAMPOS DE PIN  ---
                PinInputFields()

                Spacer(Modifier.height(64.dp))

                // --- BOTÓN ACCEPT  ---
                AppButton(
                    text = stringResource(R.string.accept_button),
                    onClick = { navController.navigate("newPassword") },
                    buttonWidth = 169.dp,
                    buttonHeight = 36.dp,
                    containerColor = MainGreen,
                    textColor = LettersAndIcons
                )

                Spacer(Modifier.height(16.dp))

                // --- BOTÓN SEND AGAIN  ---
                AppButton(
                    text = stringResource(R.string.send_again_button),
                    onClick = { /* Lógica de reenvío */ },
                    buttonWidth = 169.dp,
                    buttonHeight = 36.dp,
                    containerColor = BackgroundGreenWhiteAndLetters,
                    textColor = MainGreen,
                    borderColor = MainGreen
                )

                Spacer(Modifier.weight(1f))

                // --- SECCIÓN DE REGISTRO SOCIAL ---
                Text(
                    text = stringResource(R.string.or_sign_up_with),
                    color = LettersAndIcons.copy(alpha = 0.5f),
                    fontFamily = LeagueSpartanFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    lineHeight = 15.sp

                )

                Spacer(Modifier.height(16.dp))
                // --- ICONOS DE REDES SOCIALES  ---
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 1. Botón de Facebook
                    SocialAuthButton(
                        iconId = R.drawable.ic_facebook,
                        contentDesc = stringResource(R.string.login_with_facebook),
                        onClick = { /* Acción de login con Facebook */ },
                        size = 33.dp
                    )

                    // 2. Botón de Google
                    SocialAuthButton(
                        iconId = R.drawable.ic_google,
                        contentDesc = stringResource(R.string.login_with_google),
                        onClick = { /* Acción de login con Google */ },
                        size = 33.dp
                    )
                }

                Spacer(Modifier.height(32.dp))

                // Texto "Don't have an account?"
                Row {
                    Text(
                        text = stringResource(R.string.dont_have_account),
                        color = LettersAndIcons.copy(alpha = 0.5f),
                        fontFamily = LeagueSpartanFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        lineHeight = 15.sp
                    )
                    Text(
                        text = stringResource(R.string.sign_up_link),
                        color = MainGreen,
                        fontFamily = LeagueSpartanFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        lineHeight = 15.sp
                    )
                }
                Spacer(Modifier.height(86.dp))
            }
        }
    }
}

// Componente PIN. se usa solo en esta pantalla
@Composable
fun PinInputFields() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .height(38.8.dp)
            .padding(horizontal = 24.dp)
    ) {
        // ciclo de circulos
        repeat(6) { index ->
            Box(
                modifier = Modifier
                    .size(38.8.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, MainGreen, CircleShape)
                .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                val pinDigits = listOf("•", "•", "•", "•", "•", "•")

                Text(
                    text = pinDigits.getOrElse(index) { "" },
                    color = DarkModeGreenBar,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}