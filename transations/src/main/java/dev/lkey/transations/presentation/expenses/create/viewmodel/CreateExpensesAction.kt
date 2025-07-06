package dev.lkey.transations.presentation.expenses.create.viewmodel

/**
 * Действия со стороны VM на экран добавления расходов
 * */

sealed class CreateExpensesAction {
    data class ShowSnackBar(val message: String) : CreateExpensesAction()
}
