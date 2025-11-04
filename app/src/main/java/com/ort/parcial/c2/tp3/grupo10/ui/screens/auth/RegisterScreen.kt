package com.ort.parcial.c2.tp3.grupo10.ui.screens.auth

import InputTextString
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.auth.AuthViewModel
import com.ort.parcial.c2.tp3.grupo10.ui.components.auth.AuthButton
import com.ort.parcial.c2.tp3.grupo10.ui.components.auth.InputDateTime
import com.ort.parcial.c2.tp3.grupo10.ui.components.auth.InputTextPhone
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew2
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void

@Composable
fun RegisterScreen(modifier: Modifier = Modifier,
                   navController: NavHostController,
                   vm: AuthViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var fechaNac by remember { mutableStateOf("")}
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    val passwordsMatch = confirmPassword == password
    var passwordVisible by remember { mutableStateOf(false) }
    val viewModel: RegisterViewModel = hiltViewModel()
    val isSuccess by viewModel.isSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            navController.navigate("login") {
                popUpTo("signup") { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(R.string.create_account_title),
                color = Void,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PoppinsFamily
            )
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 50.dp))
                    .background(BackgroundGreenWhiteAndLetters)
                    .padding(horizontal = 32.dp, vertical = 40.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = stringResource(R.string.full_name),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        InputTextString(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = stringResource(R.string.full_name),
                            keyboardType = KeyboardType.Email,
                            isPassword = false,
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = stringResource(R.string.username_lbl_input),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        InputTextString(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = stringResource(R.string.username_pholder_input),
                            keyboardType = KeyboardType.Email,
                            isPassword = false,
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = stringResource(R.string.mobile_number_lbl_text),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextPhone(
                            value = mobileNumber,
                            onValueChange = { mobileNumber = it },
                            placeholder = stringResource(R.string.mobile_number_pholder_text),
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.birth_date_lbl_text),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputDateTime(
                            value = fechaNac,
                            onValueChange = { fechaNac = it },
                            placeholder = stringResource(R.string.birth_date_pholder_input),
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = stringResource(R.string.password_lbl_input),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextString(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = stringResource(R.string.password_pholder_input),
                            isPassword = true,
                            keyboardType = KeyboardType.Password,
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.password_confirm_lbl_input),
                            color = Void,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextString(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholder = stringResource(R.string.password_confirm_lbl_input),
                            isPassword = true,
                            keyboardType = KeyboardType.Password,
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )

                        if (!passwordsMatch && confirmPassword.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.password_missmatch),
                                color = Color.Red,
                                fontSize = 12.sp,
                                fontFamily = PoppinsFamily,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp))
                    {
                        Row(){
                            Text(
                                text = buildAnnotatedString {
                                append("By continuing, you agree to\n")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Terms of Use")
                                }
                                append(" and ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Privacy Policy")
                                }
                                append(".")
                            },
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                fontFamily = PoppinsFamily,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        AuthButton(
                            text = if (isLoading) stringResource(R.string.loading_message) else stringResource(R.string.signup_btn_text),
                            onClick = {
                                if (!isLoading) {
                                    viewModel.signUp()
                                }
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp),
                            contentColor = Color.White,
                            containerColor = Caribbean
                        )
                        error?.let {
                            Text(text = it, color = Color.Red)
                        }
                        if (isLoading) {
                            CircularProgressIndicator(color = Caribbean)
                        }
                        Row {
                            Text(text= stringResource(R.string.already_account),
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = PoppinsFamily)
                            Text(
                                text = stringResource(R.string.login_btn_text),
                                color = Caribbean,
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = PoppinsFamily,
                                modifier = Modifier.clickable {
//                                onSignUpClick()
                                    navController.navigate("login")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

