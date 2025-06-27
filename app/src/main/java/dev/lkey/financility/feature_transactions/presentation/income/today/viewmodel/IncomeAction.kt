package dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel

/**
 * Действия со стороны VM на экран доходов
 * */

sealed class IncomeAction {
    data class ShowSnackBar(val message: String) : IncomeAction()
}
