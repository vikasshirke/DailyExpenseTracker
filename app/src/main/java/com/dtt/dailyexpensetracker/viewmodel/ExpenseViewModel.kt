package com.dtt.dailyexpensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dtt.dailyexpensetracker.data.database.ExpenseDatabase
import com.dtt.dailyexpensetracker.data.entity.CategoryTotal
import com.dtt.dailyexpensetracker.data.entity.Expense
import com.dtt.dailyexpensetracker.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: ExpenseRepository

    val allExpenses: StateFlow<List<Expense>>
    val categoryTotals: StateFlow<List<CategoryTotal>>

    init {
        val dao = ExpenseDatabase.getDatabase(application).expenseDao()
        repo = ExpenseRepository(dao)
        allExpenses = repo.allExpenses.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        categoryTotals = repo.getCategoryTotals().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    }

    fun addExpense(expense: Expense) = viewModelScope.launch {
        repo.insert(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repo.update(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repo.delete(expense)
    }
}
