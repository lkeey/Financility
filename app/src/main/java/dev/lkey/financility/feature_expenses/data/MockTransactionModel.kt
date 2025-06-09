package dev.lkey.financility.feature_expenses.data

data class MockTransactionModel (
    val emoji: String? = null,
    val title: String,
    val description: String? = null,
    val cost: String = "100 000 ₽",
)