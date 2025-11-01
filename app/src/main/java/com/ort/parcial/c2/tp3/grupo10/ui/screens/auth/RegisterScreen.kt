package com.ort.parcial.c2.tp3.grupo10.ui.screens.auth

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.ort.parcial.c2.tp3.grupo10.ui.components.AuthButton
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
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            textStyle = LocalTextStyle.current.copy(color =Honeydew2),
                            placeholder = { Text("Full name") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
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
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            textStyle = LocalTextStyle.current.copy(color =Honeydew2),
                            placeholder = { Text("example@example.com") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
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
                        OutlinedTextField(
                            value = mobileNumber,
                            onValueChange = { input ->
                               var filtrado = input.filter { it.isDigit() }
                                if (filtrado.length <= 10) {
                                    mobileNumber = filtrado
                                }else{
                                    mobileNumber = ""
                                }
                            },
                            textStyle = LocalTextStyle.current.copy(color =Honeydew2),
                            placeholder = { Text("11 2222 3333") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
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


                        OutlinedTextField(
                            value = fechaNac,
                            onValueChange = { input ->
                                val digits = input.filter { it.isDigit() }
                                val limited = digits.take(8)
                                fechaNac = buildString {
                                    for (i in limited.indices) {
                                        append(limited[i])
                                        if (i == 1 || i == 3) append('/')
                                    }
                                }
                            },
                            textStyle = LocalTextStyle.current.copy(color = Honeydew2),
                            placeholder = { Text("dd/mm/yyyy") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        // Password
                        Text(
                            text = "Password",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { input -> password = input },
                            textStyle = LocalTextStyle.current.copy(color = Honeydew2),
                            placeholder = { Text("••••••••")  },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val icon = if (passwordVisible) R.drawable.ic_eye_pass else R.drawable.ic_eye_off
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Image(painter = painterResource(id = icon),
                                        contentDescription = "Toggle password visibility",)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
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
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { input -> confirmPassword = input },
                            textStyle = LocalTextStyle.current.copy(color = Honeydew2),
                            placeholder = { Text("••••••••")  },
                            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val icon = if (confirmPasswordVisible) R.drawable.ic_eye_pass else R.drawable.ic_eye_off
                                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                    Image(painter = painterResource(id = icon),
                                        contentDescription = "Toggle password visibility",)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            isError = !passwordsMatch && confirmPassword.isNotEmpty(),
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                errorBorderColor = Color.Red,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
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

