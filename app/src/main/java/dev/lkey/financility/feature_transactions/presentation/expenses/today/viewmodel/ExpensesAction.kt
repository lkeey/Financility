package dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel

/**
 * Действия со стороны VM на экран расходов
 * */

sealed class ExpensesAction {
    data class ShowSnackBar(val message: String) : ExpensesAction()
}
