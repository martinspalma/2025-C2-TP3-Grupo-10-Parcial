package com.ort.parcial.c2.tp3.grupo10.data.repository

import android.content.Context
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.domain.model.Transaction
import com.ort.parcial.c2.tp3.grupo10.domain.model.TransactionType
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class FakeTransactionsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : TransactionsRepository {

    override suspend fun getTransactions(): List<Transaction> {
        val expenses = readExpensesFromAssets()
        val incomes = mockIncomes()
        return (expenses + incomes).sortedByDescending { it.date + "T" + it.time }
    }

    private fun readExpensesFromAssets(): List<Transaction> {
        return try {
            val json = context.assets.open("expenses.json").bufferedReader().use { it.readText() }
            val root = JSONObject(json)
            val array = root.getJSONArray("expenses")
            val list = mutableListOf<Transaction>()
            for (i in 0 until array.length()) {
                val o = array.getJSONObject(i)
                val id = o.optString("id", i.toString())
                val title = o.optString("title", "Expense")
                val amount = o.optDouble("amount", 0.0)
                val date = o.optString("date", "2024-01-01")
                val time = o.optString("time", "00:00")
                val category = o.optString("category", "Others")
                val icon = when (category) {
                    "Food" -> R.drawable.svg_food
                    "Transport" -> R.drawable.svg_transport
                    "Rent" -> R.drawable.svg_rent
                    "Groceries" -> R.drawable.svg_groceries
                    "Medicine" -> R.drawable.svg_medicine
                    "Entertainment" -> R.drawable.svg_entertainment
                    "Savings" -> R.drawable.svg_savings
                    else -> R.drawable.ic_expense
                }
                list.add(
                    Transaction(
                        id = "e-$id",
                        title = title,
                        amount = amount,
                        date = date,
                        time = time,
                        category = category,
                        type = TransactionType.EXPENSE,
                        iconResId = icon
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun mockIncomes(): List<Transaction> = listOf(
        Transaction(
            id = "i-1",
            title = "Salary",
            amount = 4000.0,
            date = "2024-04-30",
            time = "18:27",
            category = "Salary",
            type = TransactionType.INCOME,
            iconResId = R.drawable.ic_income
        ),
        Transaction(
            id = "i-2",
            title = "Upwork",
            amount = 340.0,
            date = "2024-03-20",
            time = "09:30",
            category = "Others",
            type = TransactionType.INCOME,
            iconResId = R.drawable.ic_income
        ),
        Transaction(
            id = "i-3",
            title = "Payments",
            amount = 120.0,
            date = "2024-04-24",
            time = "17:00",
            category = "Others",
            type = TransactionType.INCOME,
            iconResId = R.drawable.ic_income
        )
    )
}

