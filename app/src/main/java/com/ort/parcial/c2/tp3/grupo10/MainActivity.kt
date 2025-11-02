package com.ort.parcial.c2.tp3.grupo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword.ForgotPasswordScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.auth.LoginScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.auth.RegisterScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.WelcomeScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword.NewPasswordScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword.SecurityPinScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.forgotpassword.SuccessConfirmationScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.home.HomeScreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MyApplicationTheme
import com.ort.parcial.c2.tp3.grupo10.ui.screens.onboarding.OnboardingScreen1
import com.ort.parcial.c2.tp3.grupo10.ui.screens.onboarding.OnboardingScreen2
import com.ort.parcial.c2.tp3.grupo10.ui.screens.categories.CategoriesScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.expenses.ExpensesScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import com.ort.parcial.c2.tp3.grupo10.ui.screens.expenses.AddExpenseScreen




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
                    startDestination = "categories"  
                ) {
                    composable("categories") { CategoriesScreen(navController = navController) }
                    composable(
                        route = "expenses/{categoryName}",
                        arguments = listOf(
                            navArgument("categoryName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Food"
                        ExpensesScreen(
                            categoryName = categoryName,
                            navController = navController
                        )
                    }
                    
                    composable("forgotPassword") { ForgotPasswordScreen(navController = navController) }
                    composable("securityPin") { SecurityPinScreen(navController = navController) }
                    composable("newPassword") { NewPasswordScreen(navController = navController) }
                    composable("successConfirmation") { SuccessConfirmationScreen(navController = navController) }
                    composable("welcome") { WelcomeScreen(navController = navController) }
                    composable("login") { LoginScreen(navController = navController) }
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
                    composable("add_expense") { 
                        AddExpenseScreen(navController = navController) 
                    }
                    // Reset password flow
                    composable("security_pin") { SecurityPinScreen(navController = navController) }
                    composable("new_password") { NewPasswordScreen(navController = navController) }
                    composable("password_changed") { SuccessConfirmationScreen(navController = navController) }
                }
            }
        }
    }
}
