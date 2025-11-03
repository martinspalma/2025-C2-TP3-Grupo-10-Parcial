package com.ort.parcial.c2.tp3.grupo10.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.MainActivity2
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LeagueSpartanFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    // El tema global se inyecta desde MainActivity, por lo que aquí solo consumimos MaterialTheme.
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Column(
        
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.finwise_logo),
                contentDescription = null,
                modifier = Modifier.height(128.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // El título reaprovecha la fuente LeagueSpartan del tema y el color primario configurado.
            Text(
                text = "FinWise",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = LeagueSpartanFamily,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Mensaje introductorio con tipografía Poppins y color auxiliar de la paleta.
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.",
                color = LettersAndIcons.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = PoppinsFamily,
                    textAlign = TextAlign.Center
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Botón principal reutilizando AppButton para respetar el estilo CTA compartido.
            AppButton(
                text = "Log In",
                onClick = { navController.navigate("login") },
                
                buttonHeight = 52.dp,
                containerColor = Caribbean,
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            AppButton(
                text = "Sign Up",
                onClick = { navController.navigate("onboarding1") },
                buttonHeight = 52.dp,
                containerColor = LightGreen,
                textColor = MaterialTheme.colorScheme.primary,
                borderColor = MaterialTheme.colorScheme.primary, // opcional, para darle borde
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Texto alternativo que mantiene la tipografía global para acciones secundarias.
            Text(
                text = "Forgot Password?",
                color = LettersAndIcons.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = PoppinsFamily),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Acción adicional conservada del diseño original para abrir MainActivity2.
            Text(
                text = "",
                color = LettersAndIcons.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall.copy(fontFamily = PoppinsFamily),
                modifier = Modifier.clickable {
                    val intent = Intent(context, MainActivity2::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }

}