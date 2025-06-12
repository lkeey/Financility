package dev.lkey.financility.feature_expenses.presentation

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

data class ExpensesState (
    val transactions: List<TransactionModel> = listOf()
)