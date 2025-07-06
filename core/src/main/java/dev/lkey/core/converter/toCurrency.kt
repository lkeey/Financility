package dev.lkey.core.converter

import dev.lkey.financility.feature_bill.domain.model.CurrencyOption



fun String.toCurrency(): CurrencyOption {
    return when(this) {
        "RUB" -> CurrencyOption("RUB", "₽", "Российский рубль ₽")
        "USD" -> CurrencyOption("USD", "$", "Американский доллар $")
        "EUR" -> CurrencyOption("EUR", "€", "Евро")
        else -> CurrencyOption("NONE", "-", "NONE")
    }
}