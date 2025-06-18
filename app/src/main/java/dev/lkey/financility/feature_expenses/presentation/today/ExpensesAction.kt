package dev.lkey.financility.feature_expenses.presentation.today

sealed class ExpensesAction {
    data class ShowSnackBar(val message: String) : ExpensesAction()
}