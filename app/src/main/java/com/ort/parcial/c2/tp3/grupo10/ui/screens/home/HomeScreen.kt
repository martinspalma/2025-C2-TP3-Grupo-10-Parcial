package com.ort.parcial.c2.tp3.grupo10.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController?.navigate("security_pin") },
            colors = ButtonDefaults.buttonColors(containerColor = MainGreen)
        ) {
            Text("Reset Password Demo", color = Color.White)
        }
    }
}
