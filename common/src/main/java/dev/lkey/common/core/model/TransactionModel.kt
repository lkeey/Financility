package dev.lkey.common.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель транзакций
 * */

@Serializable
data class TransactionModel (
    val id: Int,
    val account: AccountBriefModel,
    @SerialName("category") val categoryModel: CategoryModel,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,
)