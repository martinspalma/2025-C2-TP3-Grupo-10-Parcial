package com.ort.parcial.c2.tp3.grupo10.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.domain.model.NotificationItem
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppTemplate
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew

@Composable
fun NotificationsScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = -1,
    onBottomSelect: (Int) -> Unit = {}
) {
    AppTemplate(
        title = "Notification",
        onBack = { navController?.popBackStack() },
        onBellClick = { },
        bottomSelected = bottomSelected,
        onBottomSelect = { idx ->
            when (idx) {
                0 -> navController?.navigate("home")
                1 -> navController?.navigate("analysis")
                2 -> navController?.navigate("transactions")
                3 -> navController?.navigate("categories")
                4 -> navController?.navigate("profile")
                else -> onBottomSelect(idx)
            }
        }
    ) { innerPadding ->
        Scaffold(
            containerColor = Color.Transparent,
        ) { scaffoldPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding())
                    .padding(scaffoldPadding)
                    .background(Honeydew)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                        .background(Honeydew)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    NotificationList(contentPadding = PaddingValues(0.dp))
                }
            }
        }
    }
}

@Composable
private fun NotificationList(contentPadding: PaddingValues) {
    val notifications = listOf(
        // --- Today ---
        NotificationItem(
            R.drawable.ic_recordatorio, "Reminder!",
            "Set up your automatic savings to meet your savings goal...",
            time = "17:00", date = "Today"
        ),
        NotificationItem(
            R.drawable.ic_estrella, "New Update",
            "Set up your automatic savings to meet your savings goal...",
            time = "17:00", date = "Today"
        ),

        // --- Yesterday ---
        NotificationItem(
            R.drawable.ic_dinero, "Transactions",
            "A new transaction has been registered",
            category = "Groceries | Pantry ",
            amount = " -$100.00", time = "17:00", date = "Yesterday",
            amountColor = Color(0xFF00A86B)
        ),
        NotificationItem(
            R.drawable.ic_recordatorio, "Reminder!",
            "Set up your automatic savings to meet your savings goal...",
            time = "17:00", date = "Yesterday"
        ),

        // --- This Weekend ---
        NotificationItem(
            R.drawable.ic_grafico, "Expense Record",
            "We recommend that you be more attentive to your finances.",
            time = "17:00", date = "This Weekend"
        ),
        NotificationItem(
            R.drawable.ic_dinero, "Transactions",
            "A new transaction has been registered ",
            category = "Food | Dinner ",
            amount = " -$70.00", time = "17:00", date = "This Weekend",
            amountColor = Color(0xFF00A86B)
        )
    )

    val grouped = notifications.groupBy { it.date }
    val order = listOf("Today", "Yesterday", "This Weekend")
    val orderedKeys = order.filter { it in grouped.keys } + grouped.keys.filter { it !in order }

    LazyColumn(
        modifier = Modifier
            .background(Honeydew)
            .fillMaxSize()
            .padding(contentPadding)
            .padding(vertical = 12.dp)
    ) {
        orderedKeys.forEach { fecha ->
            val itemsDelGrupo = grouped[fecha].orEmpty()

            item {
                Text(
                    text = fecha,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 40.dp, top = 20.dp, bottom = 0.dp)
                )
            }

            itemsIndexed(itemsDelGrupo) { index, noti ->
                val top = if (index == 0) 0.dp else 6.dp
                NotificationCard(
                    item = noti,
                    topPadding = top,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationScreen() {
    NotificationsScreen()
}
