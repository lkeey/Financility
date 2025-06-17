package dev.lkey.financility.feature_expenses.presentation

sealed class ExpensesAction {
    data class ShowSnackBar(val message: String) : ExpensesAction()
}