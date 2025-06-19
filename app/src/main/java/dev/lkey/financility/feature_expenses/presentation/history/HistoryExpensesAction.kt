package dev.lkey.financility.feature_expenses.presentation.history

sealed class HistoryExpensesAction {
    data class ShowSnackBar(val message: String) : HistoryExpensesAction()
}
