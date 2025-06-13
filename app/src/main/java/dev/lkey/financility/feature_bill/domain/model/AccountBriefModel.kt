package dev.lkey.financility.feature_bill.domain.model

data class AccountBriefModel(
    val id: String,
    val name: String,
    val balance: String,
    val currency: String,
)
