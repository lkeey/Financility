package dev.lkey.financility.feature_expenses.domain.model

data class AccountBriefModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
)
