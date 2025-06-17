package dev.lkey.financility.core

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.IOException

class ErrorHandler {

    fun handleException(e: Throwable): String {
        return when (e) {
            is ApiException -> "Бизнес-ошибка"

            is IOException -> "Ошибка сети :(\n Попробуйте включить интернет)"

            is ClientRequestException -> "Ошибка на стороне клиента"
            is ServerResponseException -> "Ошибка на стороне сервера"

            else -> "Произошла ошибка"
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
