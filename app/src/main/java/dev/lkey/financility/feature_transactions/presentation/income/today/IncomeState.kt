package dev.lkey.financility.feature_transactions.presentation.income.today

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.domain.model.TransactionModel

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = listOf(),
    val isLoading: Boolean = false
)