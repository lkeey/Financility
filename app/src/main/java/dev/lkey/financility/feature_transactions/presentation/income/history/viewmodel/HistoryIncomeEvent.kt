package dev.lkey.financility.feature_transactions.presentation.income.history.viewmodel

/**
 * События экрана истории доходов
 * */

sealed class HistoryIncomeEvent {
    data object OnLoadIncomes : HistoryIncomeEvent()
    data class OnChangedStartDate(val start : String) : HistoryIncomeEvent()
    data class OnChangedEndDate(val end : String) : HistoryIncomeEvent()
}
