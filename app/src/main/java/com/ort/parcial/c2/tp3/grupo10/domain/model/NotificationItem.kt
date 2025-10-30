package com.ort.parcial.c2.tp3.grupo10.domain.model

import androidx.compose.ui.graphics.Color

data class NotificationItem(
    val iconResId: Int,              // ID del recurso drawable
    val title: String,               // Título principal (ej: "Reminder!")
    val message: String,             // Mensaje de la notificación
    val time: String,                // Hora (ej: "17:00")
    val date: String,                // Grupo de fecha (ej: "Today", "Yesterday")
    val category: String? = null,    // Categoría opcional (ej: "Groceries")
    val amount: String? = null,      // Monto opcional (ej: "-$100.00")
    val amountColor: Color = Color.Companion.Black // Color para el texto del monto
)