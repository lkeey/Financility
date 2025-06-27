package dev.lkey.financility.core.network

import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.delay

/**
 * Выполняет перезапрос в случае 500-ой ошибки
 */

suspend fun <T> retryRequest(
    maxRetries: Int = 3,
    initialDelay: Long = 2000,
    maxDelay: Long = 10000,
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    var lastException: Throwable? = null

    repeat(maxRetries + 1) { attempt ->
        try {
            return block()
        } catch (e: ResponseException) {
            val code = e.response.status.value
            lastException = e

            if (code != 500) {
                throw e
            }

            if (attempt == maxRetries) {
                throw e
            }

            println("Ошибка $code, попытка ${attempt + 1}")

            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)

        } catch (e: Exception) {
            throw e
        }
    }

    throw lastException ?: Exception("Неизвестная ошибка")
}
