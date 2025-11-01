package com.ort.parcial.c2.tp3.grupo10.ui.screens.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen

@Composable
fun PasswordChangedScreen(
    navController: NavController,
    onContinue: () -> Unit = { navController.navigate("login") }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Simple success ring
        Box(
            modifier = Modifier
                .size(140.dp)
                .border(8.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(Color.White, CircleShape)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Password Has Been\nChanged Successfully",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onContinue,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("Back to Login", color = MainGreen)
        }
    }
}

