package dev.lkey.financility.feature_bill.data.model

import kotlinx.serialization.Serializable

/**
 * Модель запроса обновления счета
 * */

@Serializable
data class UpdateAccountDto (
    val name : String,
    val balance : String,
    val currency : String,
)
