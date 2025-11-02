package com.ort.parcial.c2.tp3.grupo10.ui.screens.auth

import InputTextString
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
                text = "Create account",
                color = Color.Black,
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
                            text = "Full name",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        InputTextString(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = "Full name",
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
                            text = "Username or Email",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        InputTextString(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "example@example.com",
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
                            text = "Mobile Number",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextPhone(
                            value = mobileNumber,
                            onValueChange = { mobileNumber = it },
                            placeholder = "11 2222 3333",
                            focusedBorderColor = Honeydew2,
                            unfocusedBorderColor = Honeydew,
                            focusedContainerColor = Honeydew,
                            unfocusedContainerColor = Honeydew,
                            cursorColor = Honeydew2
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Birth Date",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputDateTime(
                            value = fechaNac,
                            onValueChange = { fechaNac = it },
                            placeholder = "dd/mm/yyyy",
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
                            text = "Password",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextString(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Enter password",
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
                            text = "Confirm Password",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        InputTextString(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholder = "Confirm password",
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
                                text = "Passwords do not match",
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
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        AuthButton(text= "Sign Up",
                            {
//                                onLoginClick()
                                navController.navigate( "home")
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp),
                            contentColor = Color.White,
                            containerColor = Caribbean
                        )
                        Row {
                            Text("Already have an account? ",style = MaterialTheme.typography.bodySmall)
                            Text(
                                text = "Log In",
                                color = Caribbean,
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.bodySmall,
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

