package dev.lkey.financility.feature_expenses.presentation

sealed class ExpensesEvent {
    data object OnOpenCreateScreen : ExpensesEvent()
    data object OnLoadTransactions : ExpensesEvent()
    data class OnChangedStartDate(val start : String) : ExpensesEvent()
    data class OnChangedEndDate(val end : String) : ExpensesEvent()

}