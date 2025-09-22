package com.dtt.dailyexpensetracker.data.entity

data class CategoryTotal(
    val category: String,
    val total: Double? // nullable is OK; we'll treat null as 0.0
)