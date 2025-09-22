package com.dtt.dailyexpensetracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dtt.dailyexpensetracker.data.entity.Expense

@Composable
fun AddEditExpenseDialog(expense: Expense?, onDismiss: () -> Unit, onSave: (Expense) -> Unit) {
    var amount by remember { mutableStateOf(expense?.amount?.toString() ?: "") }
    var category by remember { mutableStateOf(expense?.category ?: "") }
    var description by remember { mutableStateOf(expense?.description ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val e = Expense(
                    id = expense?.id ?: 0,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    category = category,
                    description = description,
                    date = expense?.date ?: System.currentTimeMillis()
                )
                onSave(e)
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
        title = { Text(if (expense == null) "Add Expense" else "Edit Expense") },
        text = {
            Column {
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
            }
        }
    )
}
