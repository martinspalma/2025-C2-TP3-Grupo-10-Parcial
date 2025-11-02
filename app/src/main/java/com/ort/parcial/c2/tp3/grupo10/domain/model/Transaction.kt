package com.ort.parcial.c2.tp3.grupo10.domain.model

import com.ort.parcial.c2.tp3.grupo10.R

enum class TransactionType { INCOME, EXPENSE }

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,  // YYYY-MM-DD
    val time: String,  // HH:mm
    val category: String,
    val type: TransactionType,
    val iconResId: Int = R.drawable.svg_more
)

