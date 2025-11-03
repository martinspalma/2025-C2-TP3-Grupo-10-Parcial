package com.ort.parcial.c2.tp3.grupo10.data.repository

import com.ort.parcial.c2.tp3.grupo10.domain.model.Transaction

interface TransactionsRepository {
    suspend fun getTransactions(): List<Transaction>
}

