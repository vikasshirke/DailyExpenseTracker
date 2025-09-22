package com.dtt.dailyexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.dtt.dailyexpensetracker.ui.ExpenseScreen
import com.dtt.dailyexpensetracker.ui.theme.DailyExpenseTheme
import com.dtt.dailyexpensetracker.viewmodel.ExpenseViewModel
import com.example.dailyexpense.ui.PieChartSummaryScreen

class MainActivity : ComponentActivity() {

    private val vm: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        @OptIn(ExperimentalMaterial3Api::class)
        setContent {
            DailyExpenseTheme {
                var showSummary by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(if (showSummary) "Expense Summary" else "Expense List")
                            },
                            actions = {
                                IconButton(onClick = { showSummary = !showSummary }) {
                                    Icon(
                                        imageVector = if (showSummary)
                                            Icons.Filled.List
                                        else
                                            Icons.Filled.BarChart, // ✅ safe icon
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    if (showSummary) {
                        PieChartSummaryScreen(
                            vm,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        )
                    } else {
                        ExpenseScreen(
                            vm,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
