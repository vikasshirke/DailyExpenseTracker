package com.dtt.dailyexpensetracker.repository

import com.dtt.dailyexpensetracker.data.dao.ExpenseDao
import com.dtt.dailyexpensetracker.data.entity.CategoryTotal
import com.dtt.dailyexpensetracker.data.entity.Expense
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {

    val allExpenses: Flow<List<Expense>> = dao.getAllExpenses()

    suspend fun insert(expense: Expense) = dao.insert(expense)
    suspend fun update(expense: Expense) = dao.update(expense)
    suspend fun delete(expense: Expense) = dao.delete(expense)

    // <-- NEW
    fun getCategoryTotals(): Flow<List<CategoryTotal>> = dao.getTotalByCategory()
}
