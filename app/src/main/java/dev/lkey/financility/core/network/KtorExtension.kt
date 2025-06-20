package dev.lkey.financility.core.network

suspend fun <T> safeCall(
    block: suspend () -> T
): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Exception) {
        Result.failure(e)
    }
}
