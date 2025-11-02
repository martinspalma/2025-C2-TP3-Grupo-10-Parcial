package com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppButton
import com.ort.parcial.c2.tp3.grupo10.ui.components.PasswordInputField
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*

@Composable
fun NewPasswordScreen(navController: NavHostController) {
    val newPasswordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen) // Fondo principal: MainGreen
    ) {
        //  HEADER (Título principal) ---
        Text(
            text = stringResource(R.string.new_password_title),
            color = LettersAndIcons,
            fontSize = 30.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )

        // --- CUERPO / TARJETA  ---
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
                Spacer(Modifier.height(64.dp)) // Espacio inicial

                //  CAMPO NEW PASSWORD ---
                PasswordInputField(
                    value = newPasswordState.value,
                    onValueChange = { newPasswordState.value = it },
                    label = stringResource(R.string.new_password_label)
                )

                Spacer(Modifier.height(32.dp))

                //  CAMPO CONFIRM NEW PASSWORD ---
                PasswordInputField(
                    value = confirmPasswordState.value,
                    onValueChange = { confirmPasswordState.value = it },
                    label = stringResource(R.string.confirm_new_password_label)
                )

                Spacer(Modifier.height(120.dp))

                //  BOTÓN CHANGE PASSWORD ---
                AppButton(
                    text = stringResource(R.string.change_password_button),
                    onClick = { navController.navigate("successConfirmation") },
                    buttonWidth = 357.dp,
                    buttonHeight = 45.dp,
                    containerColor = MainGreen,
                    textColor = LettersAndIcons
                )

                Spacer(Modifier.weight(1f))
            }
        }
    }
}


