package dev.lkey.financility.feature_expenses.presentation.history

sealed class HistoryExpensesEvent {
    data object OnLoadTransactions : HistoryExpensesEvent()
    data class OnChangedStartDate(val start : String) : HistoryExpensesEvent()
    data class OnChangedEndDate(val end : String) : HistoryExpensesEvent()
}