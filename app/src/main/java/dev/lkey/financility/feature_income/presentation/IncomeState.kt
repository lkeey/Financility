package dev.lkey.financility.feature_income.presentation

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList()
)