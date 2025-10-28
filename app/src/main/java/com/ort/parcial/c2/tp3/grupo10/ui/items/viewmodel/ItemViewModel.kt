package com.ort.parcial.c2.tp3.grupo10.ui.items.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.parcial.c2.tp3.grupo10.domain.repository.Item
import com.ort.parcial.c2.tp3.grupo10.domain.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repo: ItemRepository
) : ViewModel() {
    val items = repo.getItems().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun add(title: String) = viewModelScope.launch {
        repo.addItem(Item(title = title))
    }
}