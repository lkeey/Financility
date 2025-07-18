package dev.lkey.transations.data.dto

import kotlinx.serialization.Serializable

/**
 * Модель для получения транзакции с бека
 * */

@Serializable
data class ResponseTransactionDto (
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,
)
