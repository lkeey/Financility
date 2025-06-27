package dev.lkey.financility.feature_transactions.presentation.income.history

sealed class HistoryIncomeEvent {
    data object OnLoadIncomes : HistoryIncomeEvent()
    data class OnChangedStartDate(val start : String) : HistoryIncomeEvent()
    data class OnChangedEndDate(val end : String) : HistoryIncomeEvent()
}
