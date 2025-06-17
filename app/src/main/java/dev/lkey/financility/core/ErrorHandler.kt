package dev.lkey.financility.core

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.IOException

class ErrorHandler {

    fun handleException(e: Throwable): FinancilityError {
        return when (e) {
            is ApiException -> FinancilityError.BusinessError(e.message ?: "Бизнес-ошибка")

            is IOException -> FinancilityError.NetworkError

            is ClientRequestException -> mapHttpStatus(e.response.status)
            is ServerResponseException -> FinancilityError.ServerError

            else -> FinancilityError.UnknownError(e)
        }
    }

    private fun mapHttpStatus(status: HttpStatusCode): FinancilityError {
        return when (status) {
            HttpStatusCode.Unauthorized -> FinancilityError.Unauthorized
            HttpStatusCode.NotFound -> FinancilityError.NotFound
            else -> FinancilityError.ServerError
        }
    }
}
