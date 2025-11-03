// app/src/main/java/com/ort/parcial/c2/tp3/grupo10/domain/model/Expense.kt
package com.ort.parcial.c2.tp3.grupo10.domain.model

import com.ort.parcial.c2.tp3.grupo10.R
data class Expense(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,  // Formato: "2024-04-30"
    val time: String,  // Formato: "18:27"
    val category: String,  // "Food", "Transport", etc.
    val iconResId: Int = R.drawable.svg_food  // Por defecto food icon
)