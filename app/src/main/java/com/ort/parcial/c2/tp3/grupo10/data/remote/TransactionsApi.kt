package com.ort.parcial.c2.tp3.grupo10.data.remote

import retrofit2.http.GET

// API shape based on provided sample
interface TransactionsApi {
    @GET("transactions")
    suspend fun getTransactions(): TransactionsEnvelope
}

data class TransactionsEnvelope(
    val transactions: TransactionsPayload? = null
)

data class TransactionsPayload(
    val credit_card_transactions: List<ApiTransaction>? = null,
    val bank_account_transactions: List<ApiTransaction>? = null
)

data class ApiTransaction(
    val transaction_id: String?,
    val date: String?,
    val description: String?,
    val amount: Double?,
    val currency: String?,
    val type: String? // "debit" | "credit"
)

