package dev.lkey.transations.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Модель для создания и обновления транзакции
 * */

@Serializable
data class TransactionDto (
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
)
