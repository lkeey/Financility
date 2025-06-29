package dev.lkey.financility.feature_bill.data.model

/**
 * Модель запроса обновления счета
 * */

data class UpdateAccountDto (
    val name : String,
    val balance : String,
    val currency : String,
)
