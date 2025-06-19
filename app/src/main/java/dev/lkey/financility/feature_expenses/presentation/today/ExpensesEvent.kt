package dev.lkey.financility.feature_expenses.presentation.today

sealed class ExpensesEvent {
    data object OnLoadTransactions : ExpensesEvent()
}
