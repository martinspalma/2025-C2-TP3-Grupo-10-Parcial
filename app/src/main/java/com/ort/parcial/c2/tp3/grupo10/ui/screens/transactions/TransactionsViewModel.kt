package com.ort.parcial.c2.tp3.grupo10.ui.screens.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.parcial.c2.tp3.grupo10.data.repository.TransactionsRepository
import com.ort.parcial.c2.tp3.grupo10.domain.model.Transaction
import com.ort.parcial.c2.tp3.grupo10.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

enum class TransactionsFilter { ALL, INCOME, EXPENSE }

data class MonthSection(val month: String, val items: List<Transaction>)

data class TransactionsUiState(
    val totalBalance: String = "$0.00",
    val totalIncome: String = "$0.00",
    val totalExpense: String = "$0.00",
    val sections: List<MonthSection> = emptyList(),
    val filter: TransactionsFilter = TransactionsFilter.ALL
)

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val repository: TransactionsRepository
) : ViewModel() {

    private val currency = NumberFormat.getCurrencyInstance(Locale.US)
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val _state = MutableStateFlow(TransactionsUiState())
    val state: StateFlow<TransactionsUiState> = _state

    private var all: List<Transaction> = emptyList()

    init {
        viewModelScope.launch {
            all = repository.getTransactions()
            recompute()
        }
    }

    fun setFilter(filter: TransactionsFilter) {
        _state.value = _state.value.copy(filter = filter)
        recompute()
    }

    private fun recompute() {
        val filtered = when (_state.value.filter) {
            TransactionsFilter.ALL -> all
            TransactionsFilter.INCOME -> all.filter { it.type == TransactionType.INCOME }
            TransactionsFilter.EXPENSE -> all.filter { it.type == TransactionType.EXPENSE }
        }

        val incomeTotal = all.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expenseTotal = all.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        val balance = incomeTotal - expenseTotal

        val sections = filtered
            .groupBy { LocalDate.parse(it.date, dateFormatter).monthValue to LocalDate.parse(it.date, dateFormatter).year }
            .toSortedMap(compareByDescending<Pair<Int, Int>> { it.second }.thenByDescending { it.first })
            .map { (monthYear, items) ->
                val (month, year) = monthYear
                MonthSection(monthName(month) + " $year", items.sortedByDescending { it.date + "T" + it.time })
            }

        _state.value = _state.value.copy(
            totalBalance = currency.format(balance),
            totalIncome = currency.format(incomeTotal),
            totalExpense = currency.format(expenseTotal),
            sections = sections
        )
    }

    private fun monthName(m: Int): String =
        Month.of(m).getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH)
}

private enum class Month { JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
    companion object {
        fun of(value: Int): Month = entries[value - 1]
    }
    fun getDisplayName(style: java.time.format.TextStyle, locale: Locale): String {
        val names = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
        return names[ordinal]
    }
}

