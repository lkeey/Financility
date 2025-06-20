package dev.lkey.financility.feature_transactions.presentation.expenses.history

sealed class HistoryExpensesAction {
    data class ShowSnackBar(val message: String) : HistoryExpensesAction()
}
