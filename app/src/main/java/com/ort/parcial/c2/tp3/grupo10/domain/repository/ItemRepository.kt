package com.ort.parcial.c2.tp3.grupo10.domain.repository

import kotlinx.coroutines.flow.Flow

data class Item(val id: String? = null, val title: String = "")

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun addItem(item: Item)
}