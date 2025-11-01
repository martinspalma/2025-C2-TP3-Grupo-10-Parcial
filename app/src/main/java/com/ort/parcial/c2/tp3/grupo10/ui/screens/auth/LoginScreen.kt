package com.ort.parcial.c2.tp3.grupo10.ui.screens.auth
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ort.parcial.c2.tp3.grupo10.R.drawable.ic_google
import com.ort.parcial.c2.tp3.grupo10.R.drawable.ic_facebook
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AuthButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Welcome",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = PoppinsFamily
            )
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 50.dp))
                    .background(BackgroundGreenWhiteAndLetters)
                    .padding(horizontal = 32.dp, vertical = 60.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

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
                        val inputBackground = Color(0xFFDFF7E2)
                        val inputTextColor = Color(0xFF00D09E)
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

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Password",
                            color = Color.DarkGray,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { input -> password = input  },
                            placeholder = { Text("••••••••") },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            textStyle = LocalTextStyle.current.copy(color =Honeydew2),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val icon = if (passwordVisible)R.drawable.ic_eye_pass else R.drawable.ic_eye_off
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Image(painter = painterResource(id = icon),
                                        contentDescription = "Toggle password visibility",)
                                }
                            },colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Honeydew2,
                                unfocusedBorderColor = Honeydew,
                                focusedContainerColor = Honeydew,
                                unfocusedContainerColor = Honeydew,
                                cursorColor = Honeydew2
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        AuthButton(text= "Log In",
                            {
                                onLoginClick()
                                navController.navigate( "home")
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp),
                            contentColor = Color.White,
                            containerColor = Caribbean
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        TextButton(onClick = onForgotPasswordClick) {
                            Text("Forgot Password?",
                                color = Color.Black,
                                fontFamily = PoppinsFamily,
                                style = MaterialTheme.typography.bodySmall)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        AuthButton(text= "Sign In",
                            onSignUpClick,
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp),
                            contentColor = MainGreen,
                            containerColor = LightGreen
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Use ")
                        Text(
                            text = "Fingerprint",
                            color = Caribbean,
                            fontWeight = FontWeight.Medium
                        )
                        Text(" To Access")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("or sign up with",style = MaterialTheme.typography.bodySmall)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(BackgroundGreenWhiteAndLetters, shape = CircleShape)
                                .clip(CircleShape)
                                .clickable { /* TODO: Facebook login */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = ic_facebook),
                                contentDescription = "Facebook Login",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(BackgroundGreenWhiteAndLetters, shape = CircleShape)
                                .clip(CircleShape)
                                .clickable { /* TODO: Google login */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = ic_google),
                                contentDescription = "Google Login",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text("Don’t have an account? ",style = MaterialTheme.typography.bodySmall)
                        Text(
                            text = "Sign Up",
                            color = Caribbean,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable {
                                onSignUpClick()
                                navController.navigate("register")
                            }
                        )
                    }
                }
            }
        }
    }
}
