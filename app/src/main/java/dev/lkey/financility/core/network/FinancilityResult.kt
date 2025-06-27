package dev.lkey.financility.core.network

sealed class FinancilityResult {
    data object Loading : FinancilityResult()
    data object Success : FinancilityResult()
    data object Error : FinancilityResult()
}
