package dev.lkey.financility.feature_transactions.presentation.income.today

sealed class IncomeEvent {
    data object OnLoadTodayIncomes : IncomeEvent()
}
