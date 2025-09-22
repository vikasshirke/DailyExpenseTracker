package com.example.dailyexpense.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.collectAsState
import com.dtt.dailyexpensetracker.data.entity.CategoryTotal
import com.dtt.dailyexpensetracker.viewmodel.ExpenseViewModel
import kotlin.math.min

@Composable
fun PieChartSummaryScreen(vm: ExpenseViewModel, modifier: Modifier = Modifier) {
    val totals by vm.categoryTotals.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Expense Summary by Category", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        if (totals.isEmpty()) {
            Text("No expenses yet", style = MaterialTheme.typography.bodyMedium)
            return@Column
        }

        // Pie chart area
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            PieChart(
                data = totals,
                modifier = Modifier
                    .weight(1f)
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Legend
            Column(modifier = Modifier.weight(1f)) {
                val totalSum = totals.sumOf { it.total ?: 0.0 }
                val colors = defaultColors
                totals.forEachIndexed { index, ct ->
                    val value = ct.total ?: 0.0
                    val percent = if (totalSum > 0.0) value / totalSum * 100.0 else 0.0
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(colors[index % colors.size])
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${ct.category}: ${"%.2f".format(value)} (${String.format("%.1f", percent)}%)")
                    }
                }
            }
        }
    }
}

private val defaultColors = listOf(
    Color(0xFFEF5350),
    Color(0xFF42A5F5),
    Color(0xFFFFCA28),
    Color(0xFF66BB6A),
    Color(0xFFAB47BC),
    Color(0xFFFF7043),
    Color(0xFF29B6F6),
)

@Composable
fun PieChart(data: List<CategoryTotal>, modifier: Modifier = Modifier) {
    val total = data.sumOf { it.total ?: 0.0 }
    if (total <= 0.0) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text("No data")
        }
        return
    }

    Canvas(modifier = modifier) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2f
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        var startAngle = -90f // start at top

        data.forEachIndexed { index, ct ->
            val value = (ct.total ?: 0.0)
            val sweep = (value / total * 360.0).toFloat()
            drawArc(
                color = defaultColors[index % defaultColors.size],
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = true,
                topLeft = androidx.compose.ui.geometry.Offset(centerX - radius, centerY - radius),
                size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
            )
            startAngle += sweep
        }
    }
}
