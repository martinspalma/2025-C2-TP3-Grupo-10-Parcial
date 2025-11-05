package com.ort.parcial.c2.tp3.grupo10


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ort.parcial.c2.tp3.grupo10.ui.screens.GenericConfirmationScreen
import com.ort.parcial.c2.tp3.grupo10.ui.screens.profile.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 1. CREAMOS EL NAV CONTROLLER LOCAL
                    val navController = rememberNavController()

                    // 2. CREAMOS EL NAV HOST CON TODA LA LÓGICA DE RUTAS
                    NavHost(
                        navController = navController,
                        startDestination = "profile"
                    ) {
                        // --- 1. PROFILE SCREEN ---
                        composable("profile") {ProfileScreen(navController = navController)}

                        // --- 2. EDIT PROFILE SCREEN ---
                        composable("edit_profile") { EditProfileScreen(navController = navController) }

                        // --- 3. SECURITY SCREEN ---
                        composable("security") { SecurityScreen(navController = navController) }

                        // --- 4. CHANGE PIN SCREEN ---
                        composable("change_pin") { ChangePinScreen(navController = navController) }

                        // --- 5. FINGERPRINT LIST SCREEN ---
                        composable("fingerprint_list") { FingerprintScreen(navController = navController) }

                        // --- 6. FINGERPRINT SETUP SCREEN ---
                        composable("fingerprint_setup") { FingerprintSetupScreen(navController = navController) }

                        // --- 7. USE FINGERPRINT SCREEN ---
                        composable("use_fingerprint") { UseFingerprintScreen (navController = navController) }

                        composable("terms_and_conditions") { TermsAndConditionsScreen(navController = navController) }


                        composable("successChangePinConfirmation") {GenericConfirmationScreen(navController = navController,
                            message = stringResource(com.ort.parcial.c2.tp3.grupo10.R.string.pin_changed_success),
                            destinationRoute = "profile")}

                        composable("successFingerEliminateConfirmation") {GenericConfirmationScreen(navController = navController,
                            message = stringResource(com.ort.parcial.c2.tp3.grupo10.R.string.fingerprint_deleted_success),
                            destinationRoute = "profile")}

                        composable("successCFingerPrintConfirmation") {GenericConfirmationScreen(navController = navController,
                            message = stringResource(com.ort.parcial.c2.tp3.grupo10.R.string.fingerprint_changed_success),
                            destinationRoute = "profile")}


                    }
                }
            }
        }
    }
}
