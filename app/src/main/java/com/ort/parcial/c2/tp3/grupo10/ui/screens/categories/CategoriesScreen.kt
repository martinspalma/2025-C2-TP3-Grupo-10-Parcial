package com.ort.parcial.c2.tp3.grupo10.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.components.FinancialHeader
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightBlue
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlueButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import kotlinx.coroutines.delay


data class CategoryItem(
    val name: String,
    val iconRes: Int
)

val categories = listOf(
    CategoryItem("Food", R.drawable.svg_food),
    CategoryItem("Transport", R.drawable.svg_transport),
    CategoryItem("Medicine", R.drawable.svg_medicine),
    CategoryItem("Groceries", R.drawable.svg_groceries),
    CategoryItem("Rent", R.drawable.svg_rent),
    CategoryItem("Gifts", R.drawable.svg_gift),
    CategoryItem("Savings", R.drawable.svg_savings),
    CategoryItem("Entertainment", R.drawable.svg_entertainment),
    CategoryItem("More", R.drawable.svg_more)
)

@Composable
fun CategoriesScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = 3,
    onBottomSelect: (Int) -> Unit = {}
) {
    
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selected = bottomSelected,
                onSelect = { index ->
                    when (index) {
                        0 -> navController?.navigate("home")
                        2 -> navController?.navigate("transactions")
                        3 -> navController?.navigate("categories")
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
                    title = "Categories",
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
                        .background(BackgroundGreenWhiteAndLetters)  // Cambiar LightGreen por BackgroundGreenWhiteAndLetters
                        .padding(24.dp)
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                ) {
                    // Category Grid (3x3) - scrollable
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
                            CategoryButton(
                                category = categories[0],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[0].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[1],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[1].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[2],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[2].name}")
                                }
                            )
                        }
                        
                        // Row 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryButton(
                                category = categories[3],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[3].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[4],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[4].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[5],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[5].name}")
                                }
                            )
                        }
                        
                        // Row 3
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryButton(
                                category = categories[6],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[6].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[7],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[7].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[8],
                                onClick = { 
                                    navController?.navigate("expenses/${categories[8].name}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.CategoryButton(
    category: CategoryItem,
    onClick: () -> Unit = {}
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
    
    // Column principal que contiene el cuadrado y el texto fuera
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(27.dp))
                .clickable(
                    onClick = {
                        isClicked = true  // Activar feedback visual
                        onClick()  // Ejecutar la acción
                    }
                )
                .background(
                    // OceanBlueButton cuando se hizo click (feedback temporal), LightBlue por defecto
                    color = if (isClicked) OceanBlueButton else LightBlue
                ),
            contentAlignment = Alignment.Center
        ) {
            // Icono más PEQUEÑO
            Icon(
                imageVector = ImageVector.vectorResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }
        
        // Texto FUERA del cuadrado, debajo
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.name,
            color = Void,
            fontSize = 14.sp,  
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesScreen() {
    CategoriesScreen(
        navController = null,
        bottomSelected = 3,
        onBottomSelect = {}
    )
}
