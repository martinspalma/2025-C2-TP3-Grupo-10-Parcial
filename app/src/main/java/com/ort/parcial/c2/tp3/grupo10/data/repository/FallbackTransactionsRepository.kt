package com.ort.parcial.c2.tp3.grupo10.data.repository

import com.ort.parcial.c2.tp3.grupo10.domain.model.Transaction
import javax.inject.Inject

/**
 * Repository that tries remote first and falls back to a local fake source
 * when remote returns empty or throws.
 */
class FallbackTransactionsRepository @Inject constructor(
    private val remote: RemoteTransactionsRepository,
    private val fake: FakeTransactionsRepository
) : TransactionsRepository {
    override suspend fun getTransactions(): List<Transaction> {
        return try {
            val remoteData = remote.getTransactions()
            if (remoteData.isNotEmpty()) remoteData else fake.getTransactions()
        } catch (_: Exception) {
            fake.getTransactions()
        }
    }
}

