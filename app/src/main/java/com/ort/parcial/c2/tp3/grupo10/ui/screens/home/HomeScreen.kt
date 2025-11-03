package com.ort.parcial.c2.tp3.grupo10.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.components.FinancialHeader
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*

data class HomeTransaction(
    val title: String,
    val subtitle: String,
    val category: String,
    val amount: String,
    val isNegative: Boolean,
    val iconRes: Int
)

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = 0,
    onBottomSelect: (Int) -> Unit = {}
) {
    val transactions = listOf(
        HomeTransaction("Salary", "18:27 - April 30", "Monthly", "$4.000,00", false, R.drawable.svg_savings),
        HomeTransaction("Groceries", "17:00 - April 24", "Pantry", "-$100,00", true, R.drawable.svg_groceries),
        HomeTransaction("Rent", "8:30 - April 15", "Rent", "-$674,40", true, R.drawable.svg_rent)
    )

    var selectedTab by remember { mutableStateOf("Monthly") }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
        ) {
            // Header de balance
            FinancialHeader(
                title = "Hi, Welcome Back",
                navController = navController,
                onNotificationClick = { },
                totalBalance = "$7,783.00",
                totalExpense = "-$1,187.40",
                progressPercentage = 0.30f,
                progressAmount = "$20,000.00",
                descriptiveText = "30% of your expenses, looks good."
            )

            // Contenedor redondeado del contenido principal
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                    .background(BackgroundGreenWhiteAndLetters)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .padding(bottom = paddingValues.calculateBottomPadding())
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // ---- Tarjeta Savings on Goals ----
                    SavingsCard()

                    // ---- Selector Daily/Weekly/Monthly ----
                    PeriodSelector(selectedTab) { selectedTab = it }

                    // ---- Lista de transacciones ----
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(transactions) { tx ->
                            TransactionRow(tx)
                        }
                    }
                }
            }
        }
    }
}

// -------- Tarjeta “Savings on Goals” --------
@Composable
fun SavingsCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LightGreen)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono circular del auto
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MainGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.svg_savings),
                contentDescription = "Car",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = "Savings on Goals",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                color = Void,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Revenue Last Week", color = Void, fontSize = 12.sp)
                    Text(
                        "$4.000.00",
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Bold,
                        color = Void
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("Food Last Week", color = Void, fontSize = 12.sp)
                    Text(
                        "-$100.00",
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Bold,
                        color = MainGreen
                    )
                }
            }
        }
    }
}

// -------- Selector Daily/Weekly/Monthly --------
@Composable
fun PeriodSelector(selected: String, onSelect: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE7F8F2))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        listOf("Daily", "Weekly", "Monthly").forEach { label ->
            val isSelected = selected == label
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) MainGreen else Color.Transparent)
                    .clickable { onSelect(label) }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = if (isSelected) Color.White else Void,
                    fontFamily = PoppinsFamily,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            }
        }
    }
}

// -------- Fila de transacción --------
@Composable
fun TransactionRow(tx: HomeTransaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono circular
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = tx.iconRes),
                contentDescription = tx.title,
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = tx.title,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                color = Void,
                fontSize = 16.sp
            )
            Text(
                text = tx.subtitle,
                fontFamily = PoppinsFamily,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = tx.category,
                fontFamily = PoppinsFamily,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = tx.amount,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Bold,
                color = if (tx.isNegative) MainGreen else Void,
                fontSize = 15.sp
            )
        }
    }
}
