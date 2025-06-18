package dev.lkey.financility.feature_expenses.presentation.today

sealed class ExpensesEvent {
    data object OnLoadTransactions : ExpensesEvent()
    data class OnChangedStartDate(val start : String) : ExpensesEvent()
    data class OnChangedEndDate(val end : String) : ExpensesEvent()

}