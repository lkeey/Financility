package dev.lkey.financility.core.network

sealed class FinancilityError {
    data object NetworkError : FinancilityError()
    data object ServerError : FinancilityError()
    data object Unauthorized : FinancilityError()
    data object NotFound : FinancilityError()
    data class BusinessError(val message: String) : FinancilityError()
    data class UnknownError(val exception: Throwable) : FinancilityError()
}
