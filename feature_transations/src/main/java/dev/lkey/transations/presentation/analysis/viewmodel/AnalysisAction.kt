package dev.lkey.transations.presentation.analysis.viewmodel

/**
 * Действия со стороны VM на экран анализа транзакций
 * */

sealed class AnalysisAction {
    data class ShowSnackBar(val message: String) : AnalysisAction()
}
