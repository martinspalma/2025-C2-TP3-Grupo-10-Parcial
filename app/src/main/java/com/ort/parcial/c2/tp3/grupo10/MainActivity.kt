package com.ort.parcial.c2.tp3.grupo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ort.parcial.c2.tp3.grupo10.ui.screens.auth.LoginScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.auth.RegisterScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.WelcomeScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.home.HomeScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.onboarding.OnboardingScreen1
import com.ort.parcial.c2.tp3.grupo10.ui.screens.onboarding.OnboardingScreen2
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MyApplicationTheme
import com.ort.parcial.c2.tp3.grupo10.ui.screens.password.NewPasswordScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.password.PasswordChangedScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.password.SecurityPinScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ) {
                    composable("welcome") { WelcomeScreen(navController = navController) }
                    composable("onboarding1") { OnboardingScreen1(navController = navController) }
                    composable("onboarding2") { OnboardingScreen2(navController = navController) }
                    composable("login") {
                        LoginScreen(
                            navController = navController,
                            onForgotPasswordClick = { navController.navigate("security_pin") }
                        )
                    }
                    composable("register") { RegisterScreen(navController = navController) }
                    composable("home") { HomeScreen(navController) }
                    // Reset password flow
                    composable("security_pin") { SecurityPinScreen(navController = navController) }
                    composable("new_password") { NewPasswordScreen(navController = navController) }
                    composable("password_changed") { PasswordChangedScreen(navController = navController) }
                }
            }
        }
    }
}
