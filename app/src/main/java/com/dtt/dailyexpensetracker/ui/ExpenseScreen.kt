package com.dtt.dailyexpensetracker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dtt.dailyexpensetracker.data.entity.Expense
import com.dtt.dailyexpensetracker.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(vm: ExpenseViewModel, modifier: Modifier = Modifier) {
    val expenses by vm.allExpenses.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var currentExpense by remember { mutableStateOf<Expense?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Daily Expense Tracker") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { 
                currentExpense = null
                showDialog = true
            }) { Text("+") }
        },
        modifier = modifier // ✅ pass the modifier here
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(expenses) { expense ->
                ExpenseItem(expense = expense,
                    onEdit = { 
                        currentExpense = it
                        showDialog = true
                    },
                    onDelete = { vm.deleteExpense(it) }
                )
            }
        }
    }

    if (showDialog) {
        AddEditExpenseDialog(
            expense = currentExpense,
            onDismiss = { showDialog = false },
            onSave = { e ->
                if (e.id == 0) vm.addExpense(e) else vm.updateExpense(e)
                showDialog = false
            }
        )
    }
}
@Composable
fun ExpenseItem(expense: Expense, onEdit: (Expense) -> Unit, onDelete: (Expense) -> Unit) {
    val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(expense.date))
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onEdit(expense) }
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = expense.category, style = MaterialTheme.typography.titleMedium)
                Text(text = expense.description)
                Text(text = date, style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(text = "$${expense.amount}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Delete", modifier = Modifier.clickable { onDelete(expense) }, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
