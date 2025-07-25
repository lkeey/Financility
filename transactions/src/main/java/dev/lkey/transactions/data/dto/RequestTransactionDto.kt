package dev.lkey.transactions.data.dto

import kotlinx.serialization.Serializable

/**
 * Модель для создания и обновления транзакции
 * */

@Serializable
data class RequestTransactionDto (
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
)
