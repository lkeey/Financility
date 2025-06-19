package dev.lkey.financility.feature_transactions.presentation.expenses.today

sealed class ExpensesEvent {
    data object OnLoadTransactions : ExpensesEvent()
}
