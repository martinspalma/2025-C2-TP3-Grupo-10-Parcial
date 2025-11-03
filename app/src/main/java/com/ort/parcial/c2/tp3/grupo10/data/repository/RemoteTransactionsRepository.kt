package com.ort.parcial.c2.tp3.grupo10.data.repository

import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.data.remote.TransactionsApi
import com.ort.parcial.c2.tp3.grupo10.data.remote.ApiTransaction
import com.ort.parcial.c2.tp3.grupo10.domain.model.Transaction
import com.ort.parcial.c2.tp3.grupo10.domain.model.TransactionType
import javax.inject.Inject

class RemoteTransactionsRepository @Inject constructor(
    private val api: TransactionsApi
) : TransactionsRepository {

    override suspend fun getTransactions(): List<Transaction> {
        return try {
            val env = api.getTransactions()
            val payload = env.transactions
            val all = mutableListOf<Transaction>()
            payload?.credit_card_transactions?.let { list ->
                all += list.mapNotNull { it.toDomain(source = "Card") }
            }
            payload?.bank_account_transactions?.let { list ->
                all += list.mapNotNull { it.toDomain(source = "Bank") }
            }
            all.sortedByDescending { it.date + "T" + it.time }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun ApiTransaction.toDomain(source: String): Transaction? {
        val id = transaction_id ?: return null
        val dateStr = date ?: return null
        val desc = description ?: "Transaction"
        val amt = amount ?: 0.0
        val t = when (type?.lowercase()) {
            "credit" -> TransactionType.INCOME
            "debit" -> TransactionType.EXPENSE
            else -> TransactionType.EXPENSE
        }
        val icon = if (t == TransactionType.INCOME) R.drawable.ic_expense else R.drawable.ic_expense
        return Transaction(
            id = id,
            title = desc,
            amount = amt,
            date = dateStr,
            time = "00:00",
            category = source,
            type = t,
            iconResId = icon
        )
    }
}

