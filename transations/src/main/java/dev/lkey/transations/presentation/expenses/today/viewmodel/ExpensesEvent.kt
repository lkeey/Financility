package dev.lkey.transations.presentation.expenses.today.viewmodel

/**
 * события экрана расходов
 * */

sealed class ExpensesEvent {
    data object OnLoadTodayExpenses : ExpensesEvent()
}
