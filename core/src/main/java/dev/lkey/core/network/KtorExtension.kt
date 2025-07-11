package dev.lkey.core.network

/**
 * Выполняет запрос и в случае успеха возвращает Result<Success, T>
 * иначе возвращает Result<Failure, Throwable>
 */

suspend fun <T> safeCall(
    block: suspend () -> T
): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Exception) {
        Result.failure(e)
    }
}
