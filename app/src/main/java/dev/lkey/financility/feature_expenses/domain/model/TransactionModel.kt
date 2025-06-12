package dev.lkey.financility.feature_expenses.domain.model

data class TransactionModel (
    val id: Int,
    val account: AccountBriefModel,
    val categoryModel: CategoryModel,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,
)