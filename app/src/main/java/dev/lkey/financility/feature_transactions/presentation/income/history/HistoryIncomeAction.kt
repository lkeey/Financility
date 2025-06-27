package dev.lkey.financility.feature_transactions.presentation.income.history

sealed class HistoryIncomeAction {
    data class ShowSnackBar(val message: String) : HistoryIncomeAction()
}
