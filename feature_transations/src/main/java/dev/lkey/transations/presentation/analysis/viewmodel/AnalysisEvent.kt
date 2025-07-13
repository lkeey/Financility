package dev.lkey.transations.presentation.analysis.viewmodel

/**
 * События экрана анализа транзакций
 * */

sealed class AnalysisEvent {
    data class OnLoadTransactions (
        val isIncome : Boolean
    ) : AnalysisEvent()

    data class OnChangedStartDate(
        val start : String,
        val isIncome : Boolean
    ) : AnalysisEvent()

    data class OnChangedEndDate(
        val end : String,
        val isIncome : Boolean
    ) : AnalysisEvent()
}
