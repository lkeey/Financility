package dev.lkey.financility.feature_expenses.presentation

sealed class ExpensesEvent {
    data object OnOpenCreateScreen : ExpensesEvent()
}