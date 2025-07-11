package dev.lkey.core.network

/**
 Используется для отображения состояния загрузки, успеха или ошибки при асинхронных действиях
 */

sealed class FinancilityResult {
    data object Loading : FinancilityResult()
    data object Success : FinancilityResult()
    data object Error : FinancilityResult()
}
