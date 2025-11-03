package com.ort.parcial.c2.tp3.grupo10.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.components.FinancialHeader
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlueButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightBlue
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ort.parcial.c2.tp3.grupo10.R
import kotlinx.coroutines.delay

data class TransactionItem(
    val name: String,
    val iconRes: Int,
    val amount: String,
    val subtitle: String,
    val color: Color
)

val transactions = listOf(
    TransactionItem("Salary", R.drawable.svg_savings, "+$4.000", "18:27 - April 30", Color(0xFF00D09E)),
    TransactionItem("Groceries", R.drawable.svg_groceries, "-$100", "17:00 - April 24", Color(0xFF00BFA6)),
    TransactionItem("Rent", R.drawable.svg_rent, "-$674,40", "8:30 - April 15", Color(0xFF009A83))
)

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = 0,
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
                // 🔹 Header con balance y progreso
                FinancialHeader(
                    title = "Good Morning",
                    navController = navController,
                    onNotificationClick = { /* TODO */ },
                    totalBalance = "$7,783.00",
                    totalExpense = "-$1,187.40",
                    progressPercentage = 0.30f,
                    progressAmount = "$20,000.00",
                    descriptiveText = "30% of your expenses, looks good."
                )

                // 🔹 Fondo blanco-verde para el contenido
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
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(transactions) { tx ->
                            TransactionCard(tx)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionCard(item: TransactionItem) {
    var isClicked by remember { mutableStateOf(false) }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(200)
            isClicked = false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(27.dp))
            .clickable {
                isClicked = true
            }
            .background(if (isClicked) OceanBlueButton else LightBlue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = item.iconRes),
            contentDescription = item.name,
            modifier = Modifier.size(40.dp),
            tint = Color.White
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = item.name,
                color = Void,
                fontSize = 16.sp,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = item.subtitle,
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = PoppinsFamily
            )
        }
        Text(
            text = item.amount,
            color = item.color,
            fontSize = 16.sp,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )
    }
}
