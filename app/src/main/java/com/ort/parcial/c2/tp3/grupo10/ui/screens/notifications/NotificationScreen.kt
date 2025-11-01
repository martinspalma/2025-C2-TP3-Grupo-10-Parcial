package com.ort.parcial.c2.tp3.grupo10.ui.screens.notifications

import com.ort.parcial.c2.tp3.grupo10.domain.model.NotificationItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ort.parcial.c2.tp3.grupo10.R

@Composable
fun NotificationScreen() {
    val notifications = listOf(
        // --- Today ---
        NotificationItem(
            iconResId = R.drawable.ic_recordatorio,
            title = "Reminder!",
            message = "Set up your automatic savings to meet your savings goal...",
            time = "17:00",
            date = "Today"
        ),
        NotificationItem(
            iconResId = R.drawable.ic_estrella,
            title = "New Update",
            message = "Set up your automatic savings to meet your savings goal...",
            time = "17:00",
            date = "Today"
        ),

        // --- Yesterday ---
        NotificationItem(
            iconResId = R.drawable.ic_dinero,
            title = "Transactions",
            message = "A new transaction has been registered",
            category = "Groceries | Pantry ",
            amount = " -$100.00",
            time = "17:00",
            date = "Yesterday",
            amountColor = Color(0xFF00A86B)
        ),
        NotificationItem(
            iconResId = R.drawable.ic_recordatorio,
            title = "Reminder!",
            message = "Set up your automatic savings to meet your savings goal...",
            time = "17:00",
            date = "Yesterday"
        ),

        // --- This Weekend ---
        NotificationItem(
            iconResId = R.drawable.ic_grafico,
            title = "Expense Record",
            message = "We recommend that you be more attentive to your finances.",
            time = "17:00",
            date = "This Weekend"
        ),
        NotificationItem(
            iconResId = R.drawable.ic_dinero,
            title = "Transactions",
            message = "A new transaction has been registered ",
            category = "Food | Dinner ",
            amount = " -$70.00",
            time = "17:00",
            date = "This Weekend",
            amountColor = Color(0xFF00A86B)
        )
    )

    val grouped = notifications.groupBy { it.date }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp)
    ) {
        grouped.forEach { (fecha, itemsDelGrupo) ->
            item {
                Text(
                    text = fecha,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 40.dp, top = 20.dp, bottom = 0.dp)
                )
            }

            itemsIndexed(itemsDelGrupo) { index, noti ->
                val top = if (index == 0) 0.dp else 6.dp
                NotificationCard(item = noti, topPadding = top)
            }


            }
        }
    }



@Preview(showBackground = true)
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen()
}
