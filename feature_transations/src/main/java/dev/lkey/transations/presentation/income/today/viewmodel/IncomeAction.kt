package dev.lkey.transations.presentation.income.today.viewmodel

/**
 * Действия со стороны VM на экран доходов
 * */

sealed class IncomeAction {
    data class ShowSnackBar(val message: String) : IncomeAction()
}
