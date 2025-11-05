package com.ort.parcial.c2.tp3.grupo10.ui.screens.savings

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.MainActivity2
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.components.FinancialHeader
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightBlue
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlueButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void
import kotlinx.coroutines.delay




data class SavingsItem(
    val name: String,
    val iconRes: Int
)

val savingsItems = listOf(
    SavingsItem("Travel", R.drawable.svg_avion),
    SavingsItem("New house", R.drawable.svg_casallave),
    SavingsItem("Car", R.drawable.svg_auto),
    SavingsItem("Wedding", R.drawable.svg_anillos)
)

@Composable
fun SavingsScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = 3,
    onBottomSelect: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selected = bottomSelected,
                onSelect = { index ->
                    when (index) {
                        0 -> navController?.navigate("home")
                        2 -> navController?.navigate("transactions")
                        3 -> navController?.navigate("categories")
                        4 -> { // <-- ÍNDICE 4: BOTÓN PROFILE
                            val intent = Intent(context, MainActivity2::class.java)
                            context.startActivity(intent)
                        }
                        else -> onBottomSelect(index)
                    }
                }
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Usar el nuevo componente reutilizable
                FinancialHeader(
                    title = "Savings",
                    navController = navController,
                    onNotificationClick = { /* Handle notification click */ },
                    totalBalance = "$7,783.00",
                    totalExpense = "-$1,187.40",
                    progressPercentage = 0.30f,
                    progressAmount = "$20,000.00",
                    descriptiveText = "30% of your expenses, looks good."
                )

                // Main Content Area (Light Green/Off-White Background)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 45.dp,
                                topEnd = 45.dp
                            )
                        )
                        .background(BackgroundGreenWhiteAndLetters)
                        .padding(24.dp)
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                ) {
                    // Savings Grid - scrollable
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Row 1
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SavingsItemButton(savingsItem = savingsItems[0])
                            SavingsItemButton(savingsItem = savingsItems[1])
                            SavingsItemButton(savingsItem = savingsItems[2])
                        }

                        // Row 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SavingsItemButton(savingsItem = savingsItems[3])
                            // Espacios vacíos para mantener el layout
                            Spacer(modifier = Modifier.weight(1f))
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        // Botón "Add More" centrado
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                // TODO: Implementar acción de agregar más
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF17C3B2) // Turquoise como en la imagen
                            )
                        ) {
                            Text(
                                text = "Add More",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = PoppinsFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.SavingsItemButton(
    savingsItem: SavingsItem
) {
    // Estado para indicar que se hizo click (feedback visual)
    var isClicked by remember { mutableStateOf(false) }

    // Resetear el estado después de un tiempo corto
    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(200) // 200ms de feedback visual
            isClicked = false
        }
    }

    // Column principal que contiene el cuadrado y el texto
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cuadrado con icono
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(27.dp))
                .clickable(
                    onClick = {
                        isClicked = true
                    }
                )
                .background(
                    color = if (isClicked) OceanBlueButton else LightBlue
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = savingsItem.iconRes),
                contentDescription = savingsItem.name,
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }

        // Texto debajo del cuadrado
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = savingsItem.name,
            color = Void,
            fontSize = 14.sp,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
