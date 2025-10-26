package com.ort.parcial.c2.tp3.grupo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ort.parcial.c2.tp3.grupo10.ui.screens.LoginScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.RegisterScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.WelcomeScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = "welcome",
                    ){
                        composable("welcome"){
                            WelcomeScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("register"){
                            RegisterScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("login"){
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}