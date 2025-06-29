package dev.lkey.financility.feature_transactions.data.dto

import kotlinx.serialization.Serializable

/**
 * Модель для создания транзакции
 * */

@Serializable
data class TransactionDto (
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
)
