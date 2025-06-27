package dev.lkey.financility.core.error

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import java.io.IOException

/**
 * Класс, который конвертирует код ошибки в понятный для юзера вид
 */

class ErrorHandler {
    fun handleException(e: Throwable): String {
        return when (e) {
            is ApiException -> "Произошла ошибка\n${e.message}"

            is IOException -> "Ошибка сети :(\n Попробуйте включить интернет)"

            is ClientRequestException -> "Ошибка на стороне клиента"
            is ServerResponseException -> "Ошибка на стороне сервера"

            else -> "Произошла ошибка\n${e.message}"
        }
    }
}
