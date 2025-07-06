package dev.lkey.common.core.model

import kotlinx.serialization.Serializable

/**
 * Модель счета
 * */

@Serializable
data class AccountBriefModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
)
