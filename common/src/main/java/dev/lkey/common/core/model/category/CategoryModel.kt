package dev.lkey.common.core.model.category

import kotlinx.serialization.Serializable

/**
 * Модель статей
 * */

@Serializable
data class CategoryModel (
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)