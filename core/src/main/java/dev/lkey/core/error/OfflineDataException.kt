package dev.lkey.core.error

class OfflineDataException(
    val data: List<Any>
) : Exception("Offline cached data loaded")
