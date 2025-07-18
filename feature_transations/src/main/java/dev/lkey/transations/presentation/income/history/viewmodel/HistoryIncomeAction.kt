package dev.lkey.transations.presentation.income.history.viewmodel

/**
 * Действия со стороны VM на экран истории доходов
 * */

sealed class HistoryIncomeAction {
    data class ShowSnackBar(val message: String) : HistoryIncomeAction()
}
