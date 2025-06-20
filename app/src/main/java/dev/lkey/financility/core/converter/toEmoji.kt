package dev.lkey.financility.core.converter

fun String.toEmoji(): String {
    return when(this) {
        "RUB" -> "\u20BD"
        "USD" -> "\u0024"
        "EUR" -> "\u20AC"
        else -> this
    }
}