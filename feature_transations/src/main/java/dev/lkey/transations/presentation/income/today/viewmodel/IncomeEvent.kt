package dev.lkey.transations.presentation.income.today.viewmodel

/**
 * События экрана доходов
 * */

sealed class IncomeEvent {
    data object OnLoadTodayIncomes : IncomeEvent()
}
