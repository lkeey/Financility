package dev.lkey.financility.feature_transactions.presentation.expenses.history

sealed class HistoryExpensesEvent {
    data object OnLoadExpenses : HistoryExpensesEvent()
    data class OnChangedStartDate(val start : String) : HistoryExpensesEvent()
    data class OnChangedEndDate(val end : String) : HistoryExpensesEvent()
}