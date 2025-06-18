package dev.lkey.financility.feature_expenses.domain.model

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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