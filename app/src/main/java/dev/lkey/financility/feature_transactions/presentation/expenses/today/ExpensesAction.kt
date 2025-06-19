package dev.lkey.financility.feature_transactions.presentation.expenses.today

sealed class ExpensesAction {
    data class ShowSnackBar(val message: String) : ExpensesAction()
}