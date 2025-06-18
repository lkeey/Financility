package dev.lkey.financility.feature_expenses.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto (
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
)
