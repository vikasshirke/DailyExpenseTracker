package com.dtt.dailyexpensetracker.data.dao

import androidx.room.*
import com.dtt.dailyexpensetracker.data.entity.CategoryTotal
import com.dtt.dailyexpensetracker.data.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT SUM(amount) FROM expenses WHERE date BETWEEN :start AND :end")
    fun getTotalExpense(start: Long, end: Long): Flow<Double?>

    // <-- NEW: totals grouped by category
    @Query("SELECT category AS category, SUM(amount) AS total FROM expenses GROUP BY category")
    fun getTotalByCategory(): Flow<List<CategoryTotal>>
}
