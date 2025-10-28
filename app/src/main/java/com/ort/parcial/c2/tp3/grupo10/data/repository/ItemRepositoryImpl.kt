package com.ort.parcial.c2.tp3.grupo10.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ort.parcial.c2.tp3.grupo10.domain.repository.Item
import com.ort.parcial.c2.tp3.grupo10.domain.repository.ItemRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ItemRepository {
    private val col get() = db.collection("items")

    override fun getItems() = callbackFlow {
        val reg = col.addSnapshotListener { qs, e ->
            if (e != null) {
                trySend(emptyList())
                return@addSnapshotListener
            }
            val items = qs?.documents?.map { d ->
                Item(id = d.id, title = d.getString("title") ?: "")
            } ?: emptyList()
            trySend(items)
        }
        awaitClose { reg.remove() }
    }

    override suspend fun addItem(item: Item) {
        val data = mapOf("title" to item.title)
        col.add(data).await()
    }
}
