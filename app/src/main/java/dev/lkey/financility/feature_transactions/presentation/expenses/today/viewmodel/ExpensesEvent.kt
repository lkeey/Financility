package dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel

/**
 * события экрана расходов
 * */

sealed class ExpensesEvent {
    data object OnLoadTodayExpenses : ExpensesEvent()
}
